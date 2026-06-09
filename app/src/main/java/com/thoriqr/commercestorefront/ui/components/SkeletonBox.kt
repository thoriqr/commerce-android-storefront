package com.thoriqr.commercestorefront.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonBox(
    width: Dp,
    height: Dp,
    shape: Shape = RoundedCornerShape(4.dp)
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(shape)
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            )
    )
}