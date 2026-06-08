package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thoriqr.commercestorefront.ui.listing.section.FilterSortSection
import com.thoriqr.commercestorefront.ui.listing.section.ListingHeader
import com.thoriqr.commercestorefront.ui.listing.section.ProductGridSection

@Composable
fun ProductListingScreen(
    viewModel: ProductListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showFilterSheet by remember {
        mutableStateOf(false)
    }

    val filterCount = uiState.query.dimensions.size +
            if (
                uiState.query.priceMin != null ||
                uiState.query.priceMax != null
            ) 1 else 0

    if (showFilterSheet) {
        FilterBottomSheet(
            closeFilterSheet = {
                showFilterSheet = false
            },
            query = uiState.query,
            availableFilters = uiState.availableFilters,
            filterCount = filterCount,
            onApply = { minPrice, maxPrice, dimensions ->

                viewModel.applyFilters(
                    priceMin = minPrice,
                    priceMax = maxPrice,
                    dimensions = dimensions
                )
            },
            onClear = { viewModel.clearFilters() }
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
            },
            filterCount = filterCount,
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        ProductGridSection(
            products = uiState.products
        )
    }


}