package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategorySummaryDto(
    val id: Int,
    val name: String,
    val slugPath: String
)

@Serializable
data class CategoryInfoDto(
    val id: Int,
    val name: String,
    val description: String,
    val slug: String,
    val slugPath: String
)

@Serializable
data class CategoryDetailDto(
    val category: CategoryInfoDto,
    val breadcrumb: List<CategorySummaryDto>,
    val children: List<CategorySummaryDto>
)