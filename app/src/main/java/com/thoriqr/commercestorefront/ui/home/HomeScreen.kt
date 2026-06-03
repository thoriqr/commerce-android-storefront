package com.thoriqr.commercestorefront.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        HeroBannerCarousel(
            banners = uiState.value.banners
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        PopularCategoriesSection(
            categories = uiState.value.popularCategories,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}