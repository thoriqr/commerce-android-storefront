package com.thoriqr.commercestorefront.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoriqr.commercestorefront.ui.categories.section.CategoryTreeSection
import com.thoriqr.commercestorefront.ui.categories.skeleton.CategoriesSkeleton
import com.thoriqr.commercestorefront.ui.components.FullPageError

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            CategoriesSkeleton()
        }

        uiState.error != null -> {

            FullPageError(
                title = "Unable to load categories",
                message = uiState.error ?: "Something went wrong!",
                actionLabel = "Retry",
                onActionClick = {
                    viewModel.retry()
                }
            )
        }

        else -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(uiState.categories) {index, category ->
                    CategoryTreeSection(
                        category = category,
                        onCategoryClick = onCategoryClick
                    )

                    if (index != uiState.categories.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 16.dp),
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }




}