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
    val type =
        ProductListingType.valueOf(
            checkNotNull(
                savedStateHandle.get<String>("type")
            ).uppercase()
        )


    val value =
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
        loadInitialData()
    }

    private fun loadInitialData() {
        when (type) {
            ProductListingType.CATEGORY -> loadCategoryPage()
            ProductListingType.COLLECTION -> loadCollectionPage()
            ProductListingType.SEARCH -> loadSearchPage()
        }
    }

    fun onSortSelected(
        sort: ProductSortOption
    ) {
        _uiState.update {
            it.copy(
                query = it.query.copy(
                    sort = sort,
                    cursor = null
                )
            )
        }

        reloadProducts()
    }

    fun applyFilters(
        priceMin: Int?,
        priceMax: Int?,
        dimensions: Map<String, String>
    ) {
        _uiState.update {
            it.copy(
                query = it.query.copy(
                    priceMin = priceMin,
                    priceMax = priceMax,
                    dimensions = dimensions
                )
            )
        }

        reloadProducts()
    }

    fun clearFilters() {
        _uiState.update {
            it.copy(
                query = it.query.copy(
                    priceMin = null,
                    priceMax = null,
                    dimensions = emptyMap()
                )
            )
        }

        reloadProducts()
    }

    fun loadMoreProducts() {
//        Log.d("Pagination", "loadMoreProducts called")

        val state = _uiState.value

        if (
            state.isLoadingMore ||
            !state.hasMore ||
            state.nextCursor == null
        ) {
//            Log.d(
//                "Pagination",
//                "ignored: loading=${state.isLoadingMore}, hasMore=${state.hasMore}, cursor=${state.nextCursor}"
//            )
            return
        }

        _uiState.update {
            it.copy(
                isLoadingMore = true
            )
        }

        viewModelScope.launch {

            val query = _uiState.value.query.copy(
                cursor = state.nextCursor
            )

            val result =
                when (type) {

                    ProductListingType.CATEGORY -> {
                        productListingRepository.getByCategory(
                            slugPath = value,
                            filters = query
                        )
                    }

                    ProductListingType.COLLECTION -> {
                        productListingRepository.getByCollection(
                            slug = value,
                            filters = query
                        )
                    }

                    ProductListingType.SEARCH -> {
                        productListingRepository.getBySearch(
                            search = value,
                            filters = query
                        )
                    }
                }

            handleLoadMoreResult(result)
        }
    }

    fun retryProducts() {
        reloadProducts()
    }

    private fun reloadProducts() {

        _uiState.update {
            it.copy(
                isProductsLoading = true,
                productsError = null,
                products = emptyList(),
                hasMore = false,
                nextCursor = null
            )
        }

        viewModelScope.launch {

            val result =
                when (type) {

                    ProductListingType.CATEGORY -> {
                        productListingRepository.getByCategory(
                            slugPath = value,
                            filters = _uiState.value.query.copy(
                                cursor = null
                            )
                        )
                    }

                    ProductListingType.COLLECTION -> {
                        productListingRepository.getByCollection(
                            slug = value,
                            filters = _uiState.value.query.copy(
                                cursor = null
                            )
                        )
                    }

                    ProductListingType.SEARCH -> {
                        productListingRepository.getBySearch(
                            search = value,
                            filters = _uiState.value.query.copy(
                                cursor = null
                            )
                        )
                    }
                }

            handleProductResult(result)
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
                    filters = _uiState.value.query
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
                    filters = _uiState.value.query
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
                    search = value,
                    filters = _uiState.value.query
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
                        availableFilters = filters,
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

    private fun handleLoadMoreResult(
        result: NetworkResult<
                ApiResponseWithMeta<
                        List<ProductCardDto>,
                        CursorMetaDto
                        >
                >
    ) {
        result
            .onSuccess { response ->

//                Log.d(
//                    "Pagination",
//                    "loaded ${response.data?.size ?: 0} items, hasMore=${response.meta?.hasMore}, nextCursor=${response.meta?.nextCursor}"
//                )

                _uiState.update {
                    it.copy(
                        products = it.products + (response.data ?: emptyList()),
                        hasMore = response.meta?.hasMore ?: false,
                        nextCursor = response.meta?.nextCursor,
                        isLoadingMore = false
                    )
                }
            }
            .onError { message ->

//                Log.e(
//                    "Pagination",
//                    "load more failed: $message"
//                )
                _uiState.update {
                    it.copy(
                        productsError = message,
                        isLoadingMore = false
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