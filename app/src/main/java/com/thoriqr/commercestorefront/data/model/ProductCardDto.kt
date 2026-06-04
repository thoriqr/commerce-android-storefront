package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductCardDto(
    val id: Int,
    val name: String,
    val slug: String,
    val imageKey: String,
    val displayPrice: Int
)
