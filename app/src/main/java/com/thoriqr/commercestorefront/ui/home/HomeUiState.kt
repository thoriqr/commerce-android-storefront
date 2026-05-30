package com.thoriqr.commercestorefront.ui.home

import com.thoriqr.commercestorefront.data.model.BannerDto

data class HomeUiState(
    val banners: List<BannerDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
