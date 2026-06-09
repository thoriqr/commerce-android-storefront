package com.thoriqr.commercestorefront.ui.listing.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.ui.components.ProductCard

@Composable
fun ProductGridSection(
    products: List<ProductCardDto>,
    state: LazyGridState
) {
    LazyVerticalGrid (
        state = state,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductCard(
                product = product
            )
        }
    }
}