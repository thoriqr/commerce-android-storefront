package com.thoriqr.commercestorefront.ui.home

import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto

data class HomeUiState(
    val banners: List<BannerDto> = emptyList(),
    val popularCategories: List<PopularCategoryDto> = emptyList(),

    val isBannerLoading: Boolean = false,
    val isPopularCategoriesLoading: Boolean = false,

    val bannerError: String? = null,
    val popularCategoriesError: String? = null
)