package com.thoriqr.commercestorefront.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home: BottomNavItem(
        route = AppDestination.Home.route,
        label = "Home",
        icon = Icons.Default.Home
    )

    data object Categories: BottomNavItem(
        route = AppDestination.Categories.route,
        label = "Categories",
        icon = Icons.Default.Category
    )

    data object Account: BottomNavItem(
        route = AppDestination.Account.route,
        label = "Account",
        icon = Icons.Default.Person
    )
}