package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CollectionDetailDto(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String
)
