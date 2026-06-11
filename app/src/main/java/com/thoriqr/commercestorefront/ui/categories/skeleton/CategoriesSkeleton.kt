package com.thoriqr.commercestorefront.ui.categories.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.ui.components.SkeletonBox

@Composable
fun CategoriesSkeleton() {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(3) {

            CategoryTreeSkeleton()

            if (it < 2) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 16.dp),
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
private fun CategoryTreeSkeleton() {

    Column (
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        SkeletonBox(
            width = 140.dp,
            height = 32.dp
        )

        repeat(3) {

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SkeletonBox(
                    width = 120.dp,
                    height = 24.dp
                )

                FlowRow (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    val widths = listOf(
                        70.dp,
                        100.dp,
                        85.dp,
                        120.dp
                    )

                    widths.forEach { width ->

                        SkeletonBox(
                            width = width,
                            height = 32.dp,
                            shape = RoundedCornerShape(50)
                        )
                    }
                }
            }
        }
    }
}