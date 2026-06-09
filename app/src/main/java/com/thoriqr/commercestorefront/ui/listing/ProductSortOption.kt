package com.thoriqr.commercestorefront.ui.listing

enum class ProductSortOption(
    val label: String,
    val sortBy: String,
    val sortDir: String,
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
    ),

    PRICE_DESC(
        label = "Highest Price",
        sortBy = "price",
        sortDir = "desc",
    )
}