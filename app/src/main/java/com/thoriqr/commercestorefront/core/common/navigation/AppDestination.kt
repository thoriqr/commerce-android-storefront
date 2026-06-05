package com.thoriqr.commercestorefront.core.common.navigation

import com.thoriqr.commercestorefront.ui.listing.ProductListingType

sealed class AppDestination(
    val route: String
) {
    data object Home: AppDestination("home")

    data object Categories: AppDestination("categories")

    data object Account: AppDestination("account")

    data object ProductListing :
        AppDestination("product-listing/{type}/{value}") {

        fun createRoute(
            type: ProductListingType,
            value: String
        ): String {
            return "product-listing/${type.name.lowercase()}/$value"
        }
    }
}