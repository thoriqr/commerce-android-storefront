package com.thoriqr.commercestorefront.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDto(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val isAvailable: Boolean,
    val warning: String? = null,
    val isVariant: Boolean,
    val initialVariantId: Int,
    val category: ProductCategoryDto,
    val dimensions: List<ProductDimensionDto>,
    val variants: List<ProductVariantDto>,
)

@Serializable
data class ProductCategoryDto(
    val name: String,
    val slugPath: String
)

@Serializable
data class ProductDimensionDto(
    val key: String,
    val label: String,
    val values: List<ProductDimensionValueDto>
)

@Serializable
data class ProductDimensionValueDto(
    val key: String,
    val label: String,
    val hexColor: String? = null
)

@Serializable
data class ProductVariantDto(
    val id: Int,
    val options: List<VariantOptionDto>,
)

@Serializable
data class VariantOptionDto(
    val dimensionKey: String,
    val valueKey: String
)

@Serializable
data class ProductImage(
    val id: Int,
    val imageKey: String,
    val type: String,
    val signature: ImageSignature
)

@Serializable
data class ImageSignature(
    val dimensionKey: String,
    val valueKey: String,
)