package com.thoriqr.commercestorefront.ui.listing

import com.thoriqr.commercestorefront.data.model.ProductCardDto

data class ProductListingUiState(
    val products: List<ProductCardDto> = emptyList(),

    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,

    val error: String? = null,

    val hasMore: Boolean = false,
    val nextCursor: String? = null
)
