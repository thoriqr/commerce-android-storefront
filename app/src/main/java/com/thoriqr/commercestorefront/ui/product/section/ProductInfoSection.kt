package com.thoriqr.commercestorefront.ui.product.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.core.common.util.CurrencyFormatter
import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailDto
import com.thoriqr.commercestorefront.ui.product.ProductAvailability

@Composable
fun ProductInfoSection(
    product: ProductDetailDto,
    variant: VariantDetailDto,
    isVariantLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        if (isVariantLoading) {
            Text(
                text = CurrencyFormatter.format(variant.price),
                style = MaterialTheme.typography.headlineMedium
            )

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = CurrencyFormatter.format(variant.price),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        ProductAvailability(
            variant = variant
        )
    }
}