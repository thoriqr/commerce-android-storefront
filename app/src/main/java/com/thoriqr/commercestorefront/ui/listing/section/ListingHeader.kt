package com.thoriqr.commercestorefront.ui.listing.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.listing.ProductListingType
import com.thoriqr.commercestorefront.ui.listing.ProductListingUiState

@Composable
fun ListingHeader(
    type: ProductListingType,
    uiState: ProductListingUiState,
    search: String? = null,
) {
    when(type) {
        ProductListingType.COLLECTION -> {
            CollectionHeader(
                uiState.collectionDetail?.name.orEmpty()
            )
        }

        ProductListingType.CATEGORY -> {
            CategoryHeader(
                uiState.categoryDetail?.category?.name.orEmpty()
            )
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
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Showing products in $title",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CategoryHeader(title: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Showing products in $title",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "\"$title\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}