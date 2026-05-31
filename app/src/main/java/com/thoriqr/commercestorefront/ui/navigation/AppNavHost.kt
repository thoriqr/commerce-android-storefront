package com.thoriqr.commercestorefront.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thoriqr.commercestorefront.ui.account.AccountScreen
import com.thoriqr.commercestorefront.ui.categories.CategoriesScreen
import com.thoriqr.commercestorefront.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route
    ) {

        composable(AppDestination.Home.route) {
            HomeScreen()
        }

        composable(AppDestination.Categories.route) {
            CategoriesScreen()
        }

        composable(AppDestination.Account.route) {
            AccountScreen()
        }
    }
}