package com.thoriqr.commercestorefront.ui.listing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.onError
import com.thoriqr.commercestorefront.core.common.util.onSuccess
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.CategoryDetailDto
import com.thoriqr.commercestorefront.data.model.CollectionDetailDto
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.data.repository.CategoryRepository
import com.thoriqr.commercestorefront.data.repository.CollectionRepository
import com.thoriqr.commercestorefront.data.repository.FilterRepository
import com.thoriqr.commercestorefront.data.repository.ProductListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val productListingRepository: ProductListingRepository,
    private val categoryRepository: CategoryRepository,
    private val collectionRepository: CollectionRepository,
    private val filterRepository: FilterRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val type =
        ProductListingType.valueOf(
            checkNotNull(
                savedStateHandle.get<String>("type")
            ).uppercase()
        )


    private val value =
        checkNotNull(
            savedStateHandle.get<String>("value")
        )

    private val _uiState = MutableStateFlow(
        ProductListingUiState(
            isProductsLoading = true
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        loadScreenData()
    }

    private fun loadScreenData() {
        when (type) {
            ProductListingType.CATEGORY -> loadCategoryPage()
            ProductListingType.COLLECTION -> loadCollectionPage()
            ProductListingType.SEARCH -> loadSearchPage()
        }
    }

    private fun loadCategoryPage() {
        _uiState.update {
            it.copy(
                isDetailLoading = true,
                isFiltersLoading = true,
                isProductsLoading = true
            )
        }

        viewModelScope.launch {

            val detailDeferred = async {
                categoryRepository.getCategoryDetail(value)
            }

            val filtersDeferred = async {
                filterRepository.getCategoryFilters(value)
            }

            val productsDeferred = async {
                productListingRepository.getByCategory(
                    slugPath = value,
                    cursor = null
                )
            }

            handleCategoryDetailResult(
                detailDeferred.await()
            )

            handleFiltersResult(
                filtersDeferred.await()
            )

            handleProductResult(
                productsDeferred.await()
            )
        }
    }

    private fun loadCollectionPage() {
        _uiState.update {
            it.copy(
                isDetailLoading = true,
                isProductsLoading = true
            )
        }

        viewModelScope.launch {

            val detailDeferred = async {
                collectionRepository.getCollectionDetail(value)
            }

            val productsDeferred = async {
                productListingRepository.getByCollection(
                    slug = value,
                    cursor = null
                )
            }

            handleCollectionDetailResult(
                detailDeferred.await()
            )

            handleProductResult(
                productsDeferred.await()
            )
        }
    }

    private fun loadSearchPage() {
        _uiState.update {
            it.copy(
                isFiltersLoading = true,
                isProductsLoading = true
            )
        }

        viewModelScope.launch {

            val filtersDeferred = async {
                filterRepository.getSearchFilters(value)
            }

            val productsDeferred = async {
                productListingRepository.getBySearch(
                    query = value,
                    cursor = null
                )
            }

            handleFiltersResult(
                filtersDeferred.await()
            )

            handleProductResult(
                productsDeferred.await()
            )
        }
    }

    private fun handleCategoryDetailResult(
        result: NetworkResult<CategoryDetailDto>
    ) {
        result
            .onSuccess { detail ->

                _uiState.update {
                    it.copy(
                        categoryDetail = detail,
                        isDetailLoading = false
                    )
                }
            }
            .onError { message ->

                _uiState.update {
                    it.copy(
                        detailError = message,
                        isDetailLoading = false
                    )
                }
            }
    }

    private fun handleCollectionDetailResult(
        result: NetworkResult<CollectionDetailDto>
    ) {
        result
            .onSuccess { detail ->

                _uiState.update {
                    it.copy(
                        collectionDetail = detail,
                        isDetailLoading = false
                    )
                }
            }
            .onError { message ->

                _uiState.update {
                    it.copy(
                        detailError = message,
                        isDetailLoading = false
                    )
                }
            }
    }

    private fun handleFiltersResult(
        result: NetworkResult<List<DimensionFilterDto>>
    ) {
        result
            .onSuccess { filters ->

                _uiState.update {
                    it.copy(
                        filters = filters,
                        isFiltersLoading = false
                    )
                }
            }
            .onError { message ->

                _uiState.update {
                    it.copy(
                        filtersError = message,
                        isFiltersLoading = false
                    )
                }
            }
    }

    private fun handleProductResult(
        result: NetworkResult<
                ApiResponseWithMeta<
                        List<ProductCardDto>,
                        CursorMetaDto
                        >
                >
    ) {
        result
            .onSuccess { response ->

                _uiState.update {
                    it.copy(
                        products = response.data ?: emptyList(),
                        hasMore = response.meta?.hasMore ?: false,
                        nextCursor = response.meta?.nextCursor,
                        isProductsLoading = false
                    )
                }
            }
            .onError { message ->

                _uiState.update {
                    it.copy(
                        productsError = message,
                        isProductsLoading = false
                    )
                }
            }
    }
}