package com.thoriqr.commercestorefront.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thoriqr.commercestorefront.core.common.navigation.AppDestination
import com.thoriqr.commercestorefront.core.common.navigation.BannerUrlParser
import com.thoriqr.commercestorefront.ui.account.AccountScreen
import com.thoriqr.commercestorefront.ui.categories.CategoriesScreen
import com.thoriqr.commercestorefront.ui.home.HomeScreen
import com.thoriqr.commercestorefront.ui.listing.ProductListingScreen
import com.thoriqr.commercestorefront.ui.listing.ProductListingType

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route
    ) {

        composable(AppDestination.Home.route) {
            HomeScreen(
                onBannerClick = { banner ->
                    BannerUrlParser.parse(banner.url)?.let { destination ->
                        navController.navigate(
                            AppDestination.ProductListing.createRoute(
                                type = destination.type,
                                value = destination.value
                            )
                        )
                    }
                },
                onCategoryClick = { category ->
                    navController.navigate(
                        AppDestination.ProductListing.createRoute(
                            type = ProductListingType.CATEGORY,
                            value = category.slugPath
                        )
                    )
                },
                onCollectionClick = { collection ->
                    navController.navigate(
                        AppDestination.ProductListing.createRoute(
                            type = ProductListingType.COLLECTION,
                            value = collection.slug
                        )
                    )
                }
            )
        }

        composable(AppDestination.Categories.route) {
            CategoriesScreen()
        }

        composable(AppDestination.Account.route) {
            AccountScreen()
        }

        composable(
            route = AppDestination.ProductListing.route
        ) {
            ProductListingScreen()
        }
    }
}