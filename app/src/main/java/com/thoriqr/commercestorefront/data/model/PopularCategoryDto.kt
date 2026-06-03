package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PopularCategoryDto(
    val id: Int,
    val name: String,
    val slug: String,
    val slugPath: String,
    val totalSold: Int
)
