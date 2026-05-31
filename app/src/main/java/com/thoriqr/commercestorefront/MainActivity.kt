package com.thoriqr.commercestorefront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.thoriqr.commercestorefront.ui.components.AppBottomBar
import com.thoriqr.commercestorefront.ui.components.AppTopBar
import com.thoriqr.commercestorefront.ui.navigation.AppNavHost
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

                Scaffold(
                    topBar = {
                        AppTopBar()
                    },
                    bottomBar = {
                        AppBottomBar(navController)
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
