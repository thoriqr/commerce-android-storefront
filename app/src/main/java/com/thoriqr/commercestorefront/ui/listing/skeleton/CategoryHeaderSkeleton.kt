package com.thoriqr.commercestorefront.ui.listing.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.components.SkeletonBox

@Composable
fun CategoryHeaderSkeleton() {

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

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            repeat(3) {

                SkeletonBox(
                    width = 80.dp,
                    height = 32.dp,
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}