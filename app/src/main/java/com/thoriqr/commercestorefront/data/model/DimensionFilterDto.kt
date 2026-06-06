package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DimensionFilterDto(
    val name: String,
    val label: String,
    val values: List<DimensionFilterValueDto>
)

@Serializable
data class DimensionFilterValueDto(
    val value: String,
    val label: String,
    val count: Int,
    val hexColor: String? = null
)

