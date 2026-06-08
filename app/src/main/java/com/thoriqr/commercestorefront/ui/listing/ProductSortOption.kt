package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.ui.graphics.vector.ImageVector

enum class ProductSortOption(
    val label: String,
    val sortBy: String,
    val sortDir: String,
    val icon: ImageVector? = null
) {
    LATEST(
        label = "Latest",
        sortBy = "created_at",
        sortDir = "desc"
    ),

    PRICE_ASC(
        label = "Lowest Price",
        sortBy = "price",
        sortDir = "asc",
        icon = Icons.Outlined.ArrowUpward
    ),

    PRICE_DESC(
        label = "Highest Price",
        sortBy = "price",
        sortDir = "desc",
        icon = Icons.Outlined.ArrowDownward
    )
}