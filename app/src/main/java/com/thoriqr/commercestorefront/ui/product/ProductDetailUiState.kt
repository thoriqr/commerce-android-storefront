package com.thoriqr.commercestorefront.ui.product

import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailDto

data class ProductDetailUiState(
    val product: ProductDetailDto? = null,

    val selectedVariant: VariantDetailDto? = null,
    val selectedVariantId: Int? = null,

    val selectedOptions: Map<String, String> = emptyMap(),
    val selectedImageIndex: Int = 0,

    val isLoading: Boolean = true,
    val isVariantLoading: Boolean = false,

    val error: String? = null,
    val variantError: String? = null
)
