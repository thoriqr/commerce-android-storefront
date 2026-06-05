package com.thoriqr.commercestorefront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thoriqr.commercestorefront.core.common.util.KeyboardUtils
import com.thoriqr.commercestorefront.ui.components.AppBottomBar
import com.thoriqr.commercestorefront.ui.components.AppTopBar
import com.thoriqr.commercestorefront.ui.components.AppTopBarState
import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import com.thoriqr.commercestorefront.core.common.navigation.AppDestination
import com.thoriqr.commercestorefront.ui.navigation.AppNavHost
import com.thoriqr.commercestorefront.core.common.navigation.TopLevelDestination
import com.thoriqr.commercestorefront.ui.theme.CommerceStorefrontTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CommerceStorefrontTheme {
                val navController = rememberNavController()

                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute =
                    currentBackStackEntry?.destination?.route

                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current

                LaunchedEffect (currentRoute) {
                    KeyboardUtils.hide(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )
                }

                val showBottomBar =
                    currentRoute in TopLevelDestination.routes

                val appTopBarState =
                    when {
                        currentRoute == AppDestination.Home.route -> {
                            AppTopBarState(
                                showSearch = true,
                                showCartButton = true
                            )
                        }

                        currentRoute?.startsWith("product-listing") == true -> {
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

                Scaffold(
                    topBar = {
                        AppTopBar(
                            state = appTopBarState,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onSearchSubmit = { query ->
                                navController.navigate(
                                    AppDestination.ProductListing.createRoute(
                                        type = ProductListingType.SEARCH,
                                        value = query
                                    )
                                )
                            }
                        )
                    },
                    bottomBar = {
                        if (showBottomBar) {
                            AppBottomBar(navController)
                        }
                    }
                ) { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavHost(navController)
                    }
                }
            }
        }
    }
}
