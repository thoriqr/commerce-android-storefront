package com.thoriqr.commercestorefront.ui.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.util.onError
import com.thoriqr.commercestorefront.core.common.util.onSuccess
import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val productId =
        checkNotNull(
            savedStateHandle.get<Int>("productId")
        )

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProduct()
    }

    fun selectOption(
        dimensionKey: String,
        valueKey: String
    ) {

        val product =
            uiState.value.product ?: return

        val updatedOptions =
            uiState.value.selectedOptions.toMutableMap().apply {
                put(dimensionKey, valueKey)
            }

        val imageIndex =
            findBestImageIndex(
                product = product,
                selectedOptions = updatedOptions
            )

        _uiState.update {
            it.copy(
                selectedOptions = updatedOptions,
                selectedImageIndex =
                    imageIndex ?: it.selectedImageIndex
            )
        }

        val variantId =
            findVariantId(
                product = product,
                selectedOptions = updatedOptions
            )

        if (
            variantId != null &&
            variantId != uiState.value.selectedVariantId
        ) {
            loadVariant(variantId)
        }
    }

    fun retry() {
        _uiState.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {

            productRepository
                .getProduct(productId)
                .onSuccess { product ->

                    val selectedOptions =
                        initializeSelectedOptions(product)

                    _uiState.update {
                        it.copy(
                            product = product,
                            selectedOptions = selectedOptions,
                            isLoading = false
                        )
                    }

                    loadVariant(
                        variantId = product.initialVariantId
                    )
                }
                .onError { message ->

                    _uiState.update {
                        it.copy(
                            error = message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun loadVariant(
        variantId: Int
    ) {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isVariantLoading = true
                )
            }

            productRepository
                .getProductVariant(
                    productId = productId,
                    variantId = variantId
                )
                .onSuccess { variant ->

                    _uiState.update {
                        it.copy(
                            selectedVariant = variant,
                            selectedVariantId = variantId,
                            isVariantLoading = false
                        )
                    }
                }
                .onError { message ->

                    _uiState.update {
                        it.copy(
                            variantError = message,
                            isVariantLoading = false
                        )
                    }
                }
        }
    }

    private fun findVariantId(
        product: ProductDetailDto,
        selectedOptions: Map<String, String>
    ): Int? {

        return product.variants
            .firstOrNull { variant ->

                variant.options.all { option ->

                    selectedOptions[option.dimensionKey] ==
                            option.valueKey
                }
            }
            ?.id
    }

    private fun initializeSelectedOptions(
        product: ProductDetailDto
    ): Map<String, String> {

        val initialVariant =
            product.variants.firstOrNull {
                it.id == product.initialVariantId
            } ?: return emptyMap()

        return initialVariant.options.associate {
            it.dimensionKey to it.valueKey
        }
    }

    private fun findBestImageIndex(
        product: ProductDetailDto,
        selectedOptions: Map<String, String>
    ): Int? {

        val images = product.images

        val index = images.indexOfFirst { image ->

            if (image.type != "variant") {
                return@indexOfFirst false
            }

            val signature =
                image.signature
                    ?: return@indexOfFirst false

            selectedOptions[signature.dimensionKey] ==
                    signature.valueKey
        }

        return index.takeIf { it >= 0 }
    }

}

