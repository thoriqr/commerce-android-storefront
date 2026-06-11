package com.thoriqr.commercestorefront.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.ui.home.section.CollectionPreviewSection
import com.thoriqr.commercestorefront.ui.home.section.HeroBannerCarousel
import com.thoriqr.commercestorefront.ui.home.section.PopularCategoriesSection
import com.thoriqr.commercestorefront.ui.home.skeleton.CollectionPreviewSkeleton
import com.thoriqr.commercestorefront.ui.home.skeleton.HeroBannerCarouselSkeleton
import com.thoriqr.commercestorefront.ui.home.skeleton.PopularCategoriesSkeleton

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBannerClick: (BannerDto) -> Unit,
    onCategoryClick: (PopularCategoryDto) -> Unit,
    onCollectionClick: (CollectionPreviewDto) -> Unit,
    onProductClick: (Int) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(12.dp)
    ) {

        item {
            if (uiState.isBannerLoading) {
                HeroBannerCarouselSkeleton()
            } else {
                HeroBannerCarousel(
                    banners = uiState.banners,
                    onBannerClick = onBannerClick
                )
            }
        }

        item {
            if (uiState.isPopularCategoriesLoading) {
                PopularCategoriesSkeleton(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            } else {
                PopularCategoriesSection(
                    categories = uiState.popularCategories,
                    onCategoryClick = onCategoryClick,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        item {
            if (uiState.isCollectionPreviewLoading) {
                CollectionPreviewSkeleton(modifier = Modifier.padding(horizontal = 16.dp))
            } else {
                CollectionPreviewSection(
                    collections = uiState.collectionPreview,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onCollectionClick = onCollectionClick,
                    onProductClick = onProductClick
                )
            }
        }
    }
}