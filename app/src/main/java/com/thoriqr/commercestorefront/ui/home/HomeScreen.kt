package com.thoriqr.commercestorefront.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoriqr.commercestorefront.ui.home.section.CollectionPreviewSection
import com.thoriqr.commercestorefront.ui.home.section.HeroBannerCarousel
import com.thoriqr.commercestorefront.ui.home.section.PopularCategoriesSection
import com.thoriqr.commercestorefront.ui.home.skeleton.CollectionPreviewSkeleton
import com.thoriqr.commercestorefront.ui.home.skeleton.HeroBannerCarouselSkeleton
import com.thoriqr.commercestorefront.ui.home.skeleton.PopularCategoriesSkeleton

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            if (uiState.value.isBannerLoading) {
                HeroBannerCarouselSkeleton()
            } else {
                HeroBannerCarousel(
                    banners = uiState.value.banners
                )
            }
        }

        item {
            if (uiState.value.isPopularCategoriesLoading) {
                PopularCategoriesSkeleton(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            } else {
                PopularCategoriesSection(
                    categories = uiState.value.popularCategories,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        item {
            if (uiState.value.isCollectionPreviewLoading) {
                CollectionPreviewSkeleton(modifier = Modifier.padding(horizontal = 16.dp))
            } else {
                CollectionPreviewSection(
                    collections = uiState.value.collectionPreview,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}