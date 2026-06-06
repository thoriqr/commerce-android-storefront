package com.thoriqr.commercestorefront.ui.home.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.ui.components.ProductCard

@Composable
fun CollectionPreviewSection(
    modifier: Modifier = Modifier,
    collections: List<CollectionPreviewDto>,
    onCollectionClick: (CollectionPreviewDto) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        collections.forEach { collection ->
            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = collection.name,
                        modifier = Modifier.clickable{
                            onCollectionClick(collection)
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (collection.hasMoreProducts) {
                        Text(
                            text = "View All",
                            modifier = Modifier.clickable {
                                onCollectionClick(collection)
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(
                        items = collection.products,
                        key = { it.id }
                    ) { product ->

                        ProductCard(
                            product = product,
                            modifier = Modifier.width(180.dp)
                        )
                    }
                }
            }
        }
    }
}