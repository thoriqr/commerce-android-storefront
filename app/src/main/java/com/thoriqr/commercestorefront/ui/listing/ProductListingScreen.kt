package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thoriqr.commercestorefront.ui.components.ProductCard
import com.thoriqr.commercestorefront.ui.listing.section.FilterSortSection
import com.thoriqr.commercestorefront.ui.listing.section.ListingHeader

@Composable
fun ProductListingScreen(
    viewModel: ProductListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showFilterSheet by remember {
        mutableStateOf(false)
    }

    if (showFilterSheet) {
        FilterBottomSheet(
            closeFilterSheet = {
                showFilterSheet = false
            },
            availableFilters = uiState.availableFilters
        )
    }

    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        ListingHeader(
            type = viewModel.type,
            uiState = uiState,
            search = viewModel.value
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        FilterSortSection(
            selectedSort = uiState.query.sort,
            onSortSelected = viewModel::onSortSelected,
            onFilterClick = {
                showFilterSheet = true
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyVerticalGrid (
            columns = GridCells.Fixed(2),
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


}