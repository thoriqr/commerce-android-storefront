package com.thoriqr.commercestorefront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thoriqr.commercestorefront.core.common.util.KeyboardUtils
import com.thoriqr.commercestorefront.ui.components.AppBottomBar
import com.thoriqr.commercestorefront.ui.topbar.AppTopBar
import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import com.thoriqr.commercestorefront.core.common.navigation.AppDestination
import com.thoriqr.commercestorefront.ui.navigation.AppNavHost
import com.thoriqr.commercestorefront.core.common.navigation.TopLevelDestination
import com.thoriqr.commercestorefront.ui.theme.CommerceStorefrontTheme
import com.thoriqr.commercestorefront.ui.topbar.AppTopBarConfig
import com.thoriqr.commercestorefront.ui.topbar.AppTopBarViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CommerceStorefrontTheme {
                val navController = rememberNavController()

                val appTopBarViewModel: AppTopBarViewModel =
                    hiltViewModel()

                val topBarUiState by appTopBarViewModel.uiState.collectAsState()

                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute =
                    currentBackStackEntry?.destination?.route

                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current



                LaunchedEffect(currentRoute) {
                    KeyboardUtils.hide(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )
                }

                LaunchedEffect(currentBackStackEntry) {

                    val type =
                        currentBackStackEntry
                            ?.arguments
                            ?.getString("type")

                    val value =
                        currentBackStackEntry
                            ?.arguments
                            ?.getString("value")
                            ?.let {
                                URLDecoder.decode(
                                    it,
                                    StandardCharsets.UTF_8.toString()
                                )
                            }

                    if (type == ProductListingType.SEARCH.name.lowercase()) {

                        appTopBarViewModel.updateSearchQuery(
                            value.orEmpty()
                        )

                    } else {

                        appTopBarViewModel.clearSearchQuery()
                    }
                }

                val showBottomBar =
                    currentRoute in TopLevelDestination.routes

                val appTopBarState = AppTopBarConfig.fromRoute(currentRoute)

                Scaffold(
                    topBar = {
                        AppTopBar(
                            state = appTopBarState,
                            submittedQuery = topBarUiState.submittedQuery,
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
