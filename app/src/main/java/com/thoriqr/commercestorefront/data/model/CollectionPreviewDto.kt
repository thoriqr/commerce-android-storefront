package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CollectionPreviewDto(
    val id: Int,
    val name: String,
    val slug: String,
    val hasMoreProducts: Boolean,
    val products: List<ProductCardDto>
)
