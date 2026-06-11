package com.thoriqr.commercestorefront.ui.categories.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.CategoryTreeDto

@Composable
fun CategoryTreeSection(
    category: CategoryTreeDto,
    onCategoryClick: (String) -> Unit = {}
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Row (
            modifier = Modifier.clickable {
                onCategoryClick(category.slugPath)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }

        category.children.forEach { child ->

            CategoryGroup(
                category = child,
                onCategoryClick = onCategoryClick
            )
        }
    }
}