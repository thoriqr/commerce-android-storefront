package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VariantDetailDto(
    val variantId: Int,
    val price: Int,
    val stock: Int,
    val sku: String? = null,
    val currency: String,
    val weight: Int,
    val weightUnit: String,
    val isAvailable: Boolean,
    val warning: String? = null
)
