package com.thoriqr.commercestorefront.ui.product

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    Text("Product Detail Screen")
}