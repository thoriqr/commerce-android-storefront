package com.thoriqr.commercestorefront.ui.categories.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.CategoryTreeDto

@Composable
fun CategoryGroup(
    category: CategoryTreeDto,
    onCategoryClick: (String) -> Unit
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Row (
            modifier = Modifier.clickable {
                onCategoryClick(category.slugPath)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            category.children.forEach { grandChild ->

                Surface(
                    shape = RoundedCornerShape(50),
                    tonalElevation = 2.dp,
                    onClick = {
                        onCategoryClick(
                            grandChild.slugPath
                        )
                    }
                ) {
                    Text(
                        text = grandChild.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(
                            horizontal = 16.dp, vertical = 8.dp
                            ),
                        )
                }
            }
        }
    }
}