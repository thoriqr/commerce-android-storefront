package com.thoriqr.commercestorefront.ui.topbar

import com.thoriqr.commercestorefront.core.common.navigation.AppDestination

object AppTopBarConfig {

    fun fromRoute(
        route: String?
    ): AppTopBarState {

        return when {

            route == AppDestination.Home.route -> {

                AppTopBarState(
                    showSearch = true,
                    showCartButton = true
                )
            }

            route?.startsWith("product-listing") == true -> {

                AppTopBarState(
                    showBackButton = true,
                    showSearch = true,
                    showCartButton = true,
                    showMenuButton = true
                )
            }

            else -> {

                AppTopBarState(
                    title = "Commerce Storefront"
                )
            }
        }
    }
}