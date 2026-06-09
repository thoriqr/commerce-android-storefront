package com.thoriqr.commercestorefront.ui.listing

data class ProductListingQuery(
    val cursor: String? = null,

    val priceMin: Int? = null,
    val priceMax: Int? = null,

    val sort: ProductSortOption = ProductSortOption.LATEST,
//    val limit: Int? = 8,

    val dimensions: Map<String, String> = emptyMap()
)

fun ProductListingQuery.toQueryMap(): Map<String, String> {
    val params = mutableMapOf<String, String>()


    cursor?.let {
        params["cursor"] = it
    }

    priceMin?.let {
        params["priceMin"] = it.toString()
    }

    priceMax?.let {
        params["priceMax"] = it.toString()
    }

//    params["limit"] = limit.toString()


    params["sortBy"] = sort.sortBy
    params["sortDir"] = sort.sortDir

    params.putAll(dimensions)

    return params
}