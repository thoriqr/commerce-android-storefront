package com.thoriqr.commercestorefront.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

@Composable
fun PopularCategoriesSection(
    categories: List<PopularCategoryDto>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Popular Categories"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = categories,
                key = { it.id }
            ) { category ->

                Surface(
                    shape = RoundedCornerShape(50),
                    tonalElevation = 2.dp
                ) {
                    Text(
                        text = category.name,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }
        }
    }

}