package com.thoriqr.commercestorefront.ui.listing.section

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.core.constants.ProductConstants
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.ui.listing.ProductListingQuery

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    closeFilterSheet: () -> Unit,
    query: ProductListingQuery,
    availableFilters: List<DimensionFilterDto>,
    filterCount: Int,
    onApply: (Int?, Int?, Map<String, String>) -> Unit,
    onClear: () -> Unit
) {
    var minPrice by remember(query) {
        mutableStateOf(
            query.priceMin?.toString() ?: ""
        )
    }

    var maxPrice by remember(query) {
        mutableStateOf(
            query.priceMax?.toString() ?: ""
        )
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    var selectedDimensions by remember(query) {
        mutableStateOf(
            query.dimensions.mapValues { (_, value) ->
                value.split(",").toSet()
            }
        )
    }

    ModalBottomSheet(
        onDismissRequest = closeFilterSheet,
        sheetState = rememberModalBottomSheetState (
            skipPartiallyExpanded = true
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {

            Text(
                text =  if (filterCount > 0) {
                    "Filters ($filterCount)"
                } else {
                    "Filters"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            HorizontalDivider()

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Price Range",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = minPrice,
                onValueChange = { input ->
                    errorMessage = null

                    if (
                        input.isEmpty() ||
                        input.all(Char::isDigit)
                    ) {

                        val value = input.toLongOrNull()

                        if (
                            value == null ||
                            value <= ProductConstants.PRICE_MAX_LIMIT
                        ) {
                            minPrice = input
                        }
                    }
                },
                isError = errorMessage != null,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                placeholder  = {
                    Text("Min Price")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = maxPrice,
                onValueChange = { input ->
                    errorMessage = null

                    if (
                        input.isEmpty() ||
                        input.all(Char::isDigit)
                    ) {

                        val value = input.toLongOrNull()

                        if (
                            value == null ||
                            value <= ProductConstants.PRICE_MAX_LIMIT
                        ) {
                            maxPrice = input
                        }
                    }
                },
                isError = errorMessage != null,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                placeholder  = {
                    Text("Max Price")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            errorMessage?.let {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if(availableFilters.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                availableFilters.forEachIndexed  { index, filter ->
                    Text(
                        text = filter.label,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Row (
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState()
                        ),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filter.values.forEach { option ->

                            FilterChip(
                                selected =
                                    selectedDimensions[filter.name]
                                        ?.contains(option.value)
                                        ?: false,
                                onClick = {
                                    val currentValues =
                                        selectedDimensions[filter.name]
                                            ?: emptySet()

                                    val newValues =
                                        if (currentValues.contains(option.value)) {
                                            currentValues - option.value
                                        } else {
                                            currentValues + option.value
                                        }

                                    selectedDimensions =
                                        selectedDimensions.toMutableMap().apply {

                                            if (newValues.isEmpty()) {
                                                remove(filter.name)
                                            } else {
                                                put(filter.name, newValues)
                                            }
                                        }
                                },
                                label = {
                                    Text(option.label)
                                }
                            )
                        }
                    }

                    if (index != availableFilters.lastIndex) {
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                    }
                }

            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        minPrice = ""
                        maxPrice = ""

                        selectedDimensions = emptyMap()

                        onClear()

                        closeFilterSheet()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = {
                        errorMessage = null

                        val min = minPrice.toIntOrNull()
                        val max = maxPrice.toIntOrNull()

                        if (
                            min != null &&
                            min > ProductConstants.PRICE_MAX_LIMIT
                        ) {
                            errorMessage =
                                "Minimum price cannot exceed ${ProductConstants.PRICE_MAX_LIMIT}"

                            return@Button
                        }

                        if (
                            max != null &&
                            max > ProductConstants.PRICE_MAX_LIMIT
                        ) {
                            errorMessage =
                                "Maximum price cannot exceed ${ProductConstants.PRICE_MAX_LIMIT}"

                            return@Button
                        }

                        if (
                            min != null &&
                            max != null &&
                            min > max
                        ) {
                            errorMessage =
                                "Minimum price cannot exceed maximum price"

                            return@Button
                        }

                        onApply(
                            min,
                            max,
                            selectedDimensions.mapValues {
                                it.value.joinToString(",")
                            }
                        )

                        closeFilterSheet()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Apply")
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}