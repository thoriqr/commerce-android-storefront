package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thoriqr.commercestorefront.ui.components.ProductCard

@Composable
fun ProductListingScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = uiState.products,
            key = { it.id }
        ) { product ->
            ProductCard(
                product = product
            )
        }
    }
}