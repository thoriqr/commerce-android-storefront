package com.thoriqr.commercestorefront.ui.product

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.thoriqr.commercestorefront.data.model.VariantDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailWarningType

@Composable
fun ProductAvailability(
    variant: VariantDetailDto
) {
    when (variant.warning) {

        VariantDetailWarningType.OUT_OF_STOCK -> {
            Text(
                text = "Out of stock",
                color = MaterialTheme.colorScheme.error
            )
        }

        VariantDetailWarningType.LOW_STOCK -> {
            Text(
                text = "Low stock (${variant.stock} left)"
            )
        }

        VariantDetailWarningType.UNAVAILABLE -> {
            Text(
                text = "Unavailable",
                color = MaterialTheme.colorScheme.error
            )
        }

        null -> {
            Text(
                text = "In stock (${variant.stock})"
            )
        }
    }
}