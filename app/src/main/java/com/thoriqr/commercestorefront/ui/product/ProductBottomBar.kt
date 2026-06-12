package com.thoriqr.commercestorefront.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.core.common.util.CurrencyFormatter
import com.thoriqr.commercestorefront.data.model.VariantDetailDto

@Composable
fun ProductBottomBar(
    variant: VariantDetailDto?,
    isVariantLoading: Boolean,
    onAddToCartClick: () -> Unit
) {

    Surface (
        tonalElevation = 2.dp
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (
                isVariantLoading ||
                variant == null
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )

            } else {

                Column {
                    Text(
                        text = CurrencyFormatter.format(variant.price)
                    )

                    ProductAvailability(
                        variant = variant
                    )
                }
            }

            Button (
                onClick = onAddToCartClick,
                enabled = !isVariantLoading &&
                        variant?.isAvailable == true
            ) {
                Text("Add to Cart")
            }
        }
    }
}