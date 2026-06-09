package com.thoriqr.commercestorefront.ui.listing.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.components.ProductCardSkeleton

@Composable
fun ProductGridSkeleton() {

    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(6) {

            ProductCardSkeleton()
        }
    }
}