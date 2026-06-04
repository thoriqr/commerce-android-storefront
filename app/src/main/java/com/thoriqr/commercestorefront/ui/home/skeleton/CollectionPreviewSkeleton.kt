package com.thoriqr.commercestorefront.ui.home.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.components.ProductCardSkeleton

@Composable
fun CollectionPreviewSkeleton(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        // title
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(4) {
                ProductCardSkeleton(
                    modifier = Modifier.width(180.dp)
                )
            }
        }
    }
}