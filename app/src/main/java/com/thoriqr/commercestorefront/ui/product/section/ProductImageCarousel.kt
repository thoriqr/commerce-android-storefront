package com.thoriqr.commercestorefront.ui.product.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoriqr.commercestorefront.core.common.util.ImageUrl
import com.thoriqr.commercestorefront.data.model.ProductImage

@Composable
fun ProductImageCarousel(
    images: List<ProductImage>,
    selectedImageIndex: Int,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState (
        initialPage = 0,
        pageCount = { images.size }
    )

    LaunchedEffect (selectedImageIndex) {
        pagerState.animateScrollToPage(
            page = selectedImageIndex
        )
    }

    Column (
        modifier = modifier
    ) {

        HorizontalPager (
            state = pagerState
        ) { page ->

            AsyncImage(
                model = ImageUrl.fromKey(
                    images[page].imageKey
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            repeat(images.size) { index ->

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outlineVariant
                            }
                        )
                )
            }
        }
    }
}