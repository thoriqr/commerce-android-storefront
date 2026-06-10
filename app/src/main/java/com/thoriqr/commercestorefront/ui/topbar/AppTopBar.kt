package com.thoriqr.commercestorefront.ui.topbar

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.core.common.util.KeyboardUtils

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppTopBar(
    state: AppTopBarState,
    submittedQuery: String,
    onBackClick: () -> Unit = {},
    onSearchSubmit: (String) -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    var localQuery by remember {
        mutableStateOf("")
    }

    LaunchedEffect(submittedQuery) {
        localQuery = submittedQuery
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val isKeyboardVisible = WindowInsets.isImeVisible

    LaunchedEffect(isKeyboardVisible) {

        if (!isKeyboardVisible) {

            localQuery = submittedQuery

            focusManager.clearFocus()
        }
    }

    TopAppBar(
        navigationIcon = {
            if (state.showBackButton) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },

        title = {
            if (state.showSearch) {

                OutlinedTextField(
                    value = localQuery,
                    onValueChange = {
                        localQuery = it
                    },
                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),

                    placeholder = {
                        Text("Search products...")
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },

                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),

                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (localQuery.isNotBlank()) {
                                KeyboardUtils.hide(
                                    focusManager = focusManager,
                                    keyboardController = keyboardController
                                )

                                onSearchSubmit(
                                    localQuery.trim()
                                )
                            }
                        }
                    )
                )
            } else {
                Text(
                    text = state.title.orEmpty()
                )
            }
        },

        actions = {

            if (state.showCartButton) {
                IconButton(
                    onClick = onCartClick
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }
            }

            if (state.showMenuButton) {
                IconButton(
                    onClick = onMenuClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
    )
}