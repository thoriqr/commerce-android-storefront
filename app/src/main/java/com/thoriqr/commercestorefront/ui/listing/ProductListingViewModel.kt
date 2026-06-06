package com.thoriqr.commercestorefront.ui.listing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.onError
import com.thoriqr.commercestorefront.core.common.util.onSuccess
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.data.repository.ProductListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val repository: ProductListingRepository,
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
            isLoading = true
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {

            val result = when (type) {

                ProductListingType.COLLECTION ->
                    repository.getByCollection(
                        slug = value,
                        cursor = null
                    )

                ProductListingType.CATEGORY ->
                    repository.getByCategory(
                        slugPath = value,
                        cursor = null
                    )

                ProductListingType.SEARCH ->
                    repository.getBySearch(
                        query = value,
                        cursor = null
                    )
            }

            handleProductResult(result)
        }


    }

    private fun handleProductResult(
        result: NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>>
    ) {
        result
            .onSuccess { response ->

                _uiState.update {
                    it.copy(
                        products = response.data ?: emptyList(),
                        hasMore = response.meta?.hasMore ?: false,
                        nextCursor = response.meta?.nextCursor,
                        isLoading = false
                    )
                }
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