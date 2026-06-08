package com.thoriqr.commercestorefront.ui.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    closeFilterSheet: () -> Unit,
    availableFilters: List<DimensionFilterDto>
) {
    var minPrice by remember {
        mutableStateOf("")
    }

    var maxPrice by remember {
        mutableStateOf("")
    }

    ModalBottomSheet(
        onDismissRequest = closeFilterSheet
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
                text = "Filters",
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
                onValueChange = {
                    minPrice = it
                },
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
                onValueChange = {
                    maxPrice = it
                },
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



            if(availableFilters.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                availableFilters.forEach { filter ->
                    Text(
                        text = filter.label,
                        style = MaterialTheme.typography.titleMedium
                    )

                    FlowRow (
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filter.values.forEach { option ->

                            FilterChip(
                                selected = false,
                                onClick = {},
                                label = {
                                    Text(option.label)
                                }
                            )
                        }
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
                    onClick = {},
                    modifier = Modifier.weight(1f)
                        .height(48.dp)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                        .height(48.dp)
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