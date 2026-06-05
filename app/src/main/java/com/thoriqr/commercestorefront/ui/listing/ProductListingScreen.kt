package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProductListingScreen(
    modifier: Modifier = Modifier,
    type: ProductListingType,
    value: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = """
                Type: $type
                
                Value: $value
            """.trimIndent()
        )
    }
}