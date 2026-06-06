package com.thoriqr.commercestorefront.ui.listing

import com.thoriqr.commercestorefront.data.model.CategoryDetailDto
import com.thoriqr.commercestorefront.data.model.CollectionDetailDto
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.data.model.ProductCardDto

data class ProductListingUiState(

    val categoryDetail: CategoryDetailDto? = null,
    val collectionDetail: CollectionDetailDto? = null,

    val filters: List<DimensionFilterDto> = emptyList(),
    val products: List<ProductCardDto> = emptyList(),

    val isDetailLoading: Boolean = false,
    val detailError: String? = null,

    val isFiltersLoading: Boolean = false,
    val filtersError: String? = null,

    val isProductsLoading: Boolean = false,
    val productsError: String? = null,

    val isLoadingMore: Boolean = false,

    val hasMore: Boolean = false,
    val nextCursor: String? = null
)