package com.thoriqr.commercestorefront.ui.listing.section

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.CategorySummaryDto
import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import com.thoriqr.commercestorefront.ui.listing.ProductListingUiState
import com.thoriqr.commercestorefront.ui.listing.skeleton.CategoryHeaderSkeleton
import com.thoriqr.commercestorefront.ui.listing.skeleton.CollectionHeaderSkeleton

@Composable
fun ListingHeader(
    type: ProductListingType,
    uiState: ProductListingUiState,
    search: String? = null,
    onSubCategoryClick: (String) -> Unit = {}
) {
    when(type) {
        ProductListingType.COLLECTION -> {
            if (uiState.isDetailLoading) {
                CollectionHeaderSkeleton()
            } else {
                CollectionHeader(
                    title = uiState.collectionDetail?.name.orEmpty()
                )
            }
        }

        ProductListingType.CATEGORY -> {
            if (uiState.isDetailLoading) {
                CategoryHeaderSkeleton()
            } else {
                CategoryHeader(
                    title = uiState.categoryDetail?.category?.name.orEmpty(),
                    children = uiState.categoryDetail?.children.orEmpty(),
                    onCategoryClick = onSubCategoryClick
                )
            }
        }

        ProductListingType.SEARCH -> {
            SearchHeader(
                search.orEmpty()
            )

        }
    }
}

@Composable
private fun CollectionHeader (title: String) {
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Showing products in $title",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}

@Composable
private fun CategoryHeader(
    title: String,
    children: List<CategorySummaryDto>,
    onCategoryClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Showing products in $title",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (children.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )



            Row (
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                children.forEach { child ->

                    Surface(
                        shape = RoundedCornerShape(50),
                        tonalElevation = 2.dp,
                        onClick = {
                            onCategoryClick(child.slugPath)
                        },
                    ) {
                        Text(
                            text = child.name,
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}

@Composable
fun SearchHeader(title: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Search results",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "\"$title\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}