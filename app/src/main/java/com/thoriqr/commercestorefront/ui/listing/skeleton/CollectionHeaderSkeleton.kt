package com.thoriqr.commercestorefront.ui.listing.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.components.SkeletonBox

@Composable
fun CollectionHeaderSkeleton() {

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        SkeletonBox(
            width = 180.dp,
            height = 32.dp
        )

        SkeletonBox(
            width = 240.dp,
            height = 18.dp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}