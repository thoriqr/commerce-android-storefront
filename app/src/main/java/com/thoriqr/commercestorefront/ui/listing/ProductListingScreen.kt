package com.thoriqr.commercestorefront.ui.listing

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thoriqr.commercestorefront.ui.listing.section.FilterBottomSheet
import com.thoriqr.commercestorefront.ui.listing.section.FilterSortSection
import com.thoriqr.commercestorefront.ui.listing.section.ListingHeader
import com.thoriqr.commercestorefront.ui.listing.section.ProductGridSection
import com.thoriqr.commercestorefront.ui.listing.skeleton.ProductGridSkeleton

@SuppressLint("FrequentlyChangingValue")
@Composable
fun ProductListingScreen(
    viewModel: ProductListingViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit = {}
) {
    val gridState = rememberLazyGridState()

    val showHeader =
        gridState.firstVisibleItemIndex == 0 &&
                gridState.firstVisibleItemScrollOffset < 50

    val uiState by viewModel.uiState.collectAsState()

    var showFilterSheet by remember {
        mutableStateOf(false)
    }

    val filterCount = uiState.query.dimensions.size +
            if (
                uiState.query.priceMin != null ||
                uiState.query.priceMax != null
            ) 1 else 0

    val hasActiveFilters =
        uiState.query.dimensions.isNotEmpty() ||
                uiState.query.priceMin != null ||
                uiState.query.priceMax != null

    val shouldLoadMore by remember {
        derivedStateOf {

            val lastVisibleItem =
                gridState.layoutInfo
                    .visibleItemsInfo
                    .lastOrNull()
                    ?.index ?: 0

            val totalItems =
                gridState.layoutInfo.totalItemsCount

            val hasScrolled =
                gridState.firstVisibleItemIndex > 0 ||
                        gridState.firstVisibleItemScrollOffset > 0

            hasScrolled &&
                    lastVisibleItem >= totalItems - 2
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            viewModel.loadMoreProducts()
        }
    }

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

        AnimatedVisibility (
            visible = showHeader,
        ) {
            ListingHeader(
                type = viewModel.type,
                uiState = uiState,
                search = viewModel.value,
                onSubCategoryClick = onCategoryClick
            )
        }

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

        when {

            uiState.isProductsLoading -> {

                ProductGridSkeleton()
            }

            uiState.products.isEmpty() -> {

                EmptyProductsState(
                    hasActiveFilters = hasActiveFilters,
                    onClearFilters = {
                        viewModel.clearFilters()
                    }
                )
            }

            else -> {

                ProductGridSection(
                    products = uiState.products,
                    state = gridState,
                    isLoadingMore = uiState.isLoadingMore
                )
            }
        }
    }


}