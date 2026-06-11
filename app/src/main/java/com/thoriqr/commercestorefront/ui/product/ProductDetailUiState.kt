package com.thoriqr.commercestorefront.ui.product

import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailDto

data class ProductDetailUiState(
    val product: ProductDetailDto? = null,
    val selectedVariant: VariantDetailDto? = null,

    val isLoading: Boolean = true,

    val error: String? = null
)
