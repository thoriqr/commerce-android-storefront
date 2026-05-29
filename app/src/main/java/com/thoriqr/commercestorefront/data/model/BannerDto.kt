package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerDto(
    val id: Int,
    val title: String,
    val imageKey: String,
    val url: String
)