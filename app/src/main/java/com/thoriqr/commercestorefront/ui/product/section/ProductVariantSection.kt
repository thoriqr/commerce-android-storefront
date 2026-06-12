package com.thoriqr.commercestorefront.ui.product.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.ProductDetailDto

@Composable
fun ProductVariantSection(
    product: ProductDetailDto,
    selectedVariantId: Int?,
    selectedOptions: Map<String, String>,
    isLoading: Boolean,
    onOptionSelected: (String, String) -> Unit
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        product.dimensions.forEach { dimension ->

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = dimension.label,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                FlowRow (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    dimension.values.forEach { value ->

                        val isSelected =
                            selectedOptions[dimension.key] == value.key

                        FilterChip(
                            selected = isSelected,
                            enabled = !isLoading,
                            onClick = {
                                onOptionSelected(
                                    dimension.key,
                                    value.key
                                )
                            },
                            label = {
                                Text(value.label)
                            }
                        )
                    }
                }
            }
        }
    }
}