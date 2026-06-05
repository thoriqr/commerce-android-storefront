package com.thoriqr.commercestorefront.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.core.common.util.KeyboardUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    state: AppTopBarState,
    onBackClick: () -> Unit = {},
    onSearchSubmit: (String) -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val (searchQuery, setSearchQuery) =
        remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    value = searchQuery,
                    onValueChange = setSearchQuery,
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
                            if (searchQuery.isNotBlank()) {
                                KeyboardUtils.hide(
                                    focusManager = focusManager,
                                    keyboardController = keyboardController
                                )

                                onSearchSubmit(
                                    searchQuery.trim()
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