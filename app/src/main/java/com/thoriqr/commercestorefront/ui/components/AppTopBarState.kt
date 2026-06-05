package com.thoriqr.commercestorefront.ui.components

data class AppTopBarState(
    val title: String? = null,

    val showBackButton: Boolean = false,

    val showSearch: Boolean = false,

    val showCartButton: Boolean = false,

    val showMenuButton: Boolean = false
)
