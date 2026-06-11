package com.thoriqr.commercestorefront.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thoriqr.commercestorefront.core.common.navigation.AppDestination
import com.thoriqr.commercestorefront.core.common.navigation.BannerUrlParser
import com.thoriqr.commercestorefront.ui.account.AccountScreen
import com.thoriqr.commercestorefront.ui.categories.CategoriesScreen
import com.thoriqr.commercestorefront.ui.home.HomeScreen
import com.thoriqr.commercestorefront.ui.listing.ProductListingScreen
import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import com.thoriqr.commercestorefront.ui.product.ProductDetailScreen

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
                },
                onProductClick = { productId ->
                    navController.navigate(
                        AppDestination.ProductDetail.createRoute(productId)
                    )
                },
            )
        }

        composable(AppDestination.Categories.route) {
            CategoriesScreen(
                onCategoryClick = { slugPath  ->
                    navController.navigate(
                        AppDestination.ProductListing.createRoute(
                            type = ProductListingType.CATEGORY,
                            value = slugPath
                        )
                    )
                },
            )
        }

        composable(AppDestination.Account.route) {
            AccountScreen()
        }

        composable(
            route = AppDestination.ProductListing.route
        ) {
            ProductListingScreen(
                onProductClick = { productId ->
                    navController.navigate(
                        AppDestination.ProductDetail.createRoute(productId)
                    )
                },
                onCategoryClick = { slugPath  ->
                    navController.navigate(
                        AppDestination.ProductListing.createRoute(
                            type = ProductListingType.CATEGORY,
                            value = slugPath
                        )
                    )
                },
                onBackToHome = {
                    navController.navigate(
                        AppDestination.Home.route
                    ) {
                        popUpTo(AppDestination.Home.route) {
                            inclusive = false
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppDestination.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) {
            ProductDetailScreen()
        }
    }
}