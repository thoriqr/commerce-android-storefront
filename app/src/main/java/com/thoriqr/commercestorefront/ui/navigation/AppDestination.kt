package com.thoriqr.commercestorefront.ui.navigation

sealed class AppDestination(
    val route: String
) {
    data object Home: AppDestination("home")

    data object Categories: AppDestination("categories")

    data object Account: AppDestination("account")
}