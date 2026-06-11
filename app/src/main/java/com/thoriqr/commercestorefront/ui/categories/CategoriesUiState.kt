package com.thoriqr.commercestorefront.ui.categories

import com.thoriqr.commercestorefront.data.model.CategoryTreeDto

data class CategoriesUiState(
    val categories: List<CategoryTreeDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
