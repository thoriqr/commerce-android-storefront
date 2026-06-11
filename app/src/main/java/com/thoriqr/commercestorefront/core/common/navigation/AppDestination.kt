package com.thoriqr.commercestorefront.core.common.navigation

import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
            val encodedValue =
                URLEncoder.encode(
                    value,
                    StandardCharsets.UTF_8.toString()
                )
            return "product-listing/${type.name.lowercase()}/$encodedValue"
        }
    }

    data object ProductDetail :
        AppDestination("product/{productId}") {

        fun createRoute(
            productId: Int
        ): String {
            return "product/$productId"
        }
    }
}