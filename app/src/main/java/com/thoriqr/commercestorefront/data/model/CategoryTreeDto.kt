package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryTreeDto(
    val id: Int,
    val parentId: Int? = null,
    val name: String,
    val slug: String,
    val slugPath: String,
    val children: List<CategoryTreeDto>
)
