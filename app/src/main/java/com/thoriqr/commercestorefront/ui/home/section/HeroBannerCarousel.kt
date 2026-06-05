package com.thoriqr.commercestorefront.ui.home.section

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoriqr.commercestorefront.core.common.util.ImageUrl
import com.thoriqr.commercestorefront.data.model.BannerDto

@Composable
fun HeroBannerCarousel(
    banners: List<BannerDto>,
    onBannerClick: (BannerDto) -> Unit
) {
    val pagerState = rememberPagerState {
        banners.size
    }

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 6.dp
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    onBannerClick(banners[page])
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageUrl.fromKey(banners[page].imageKey),
                        contentDescription = banners[page].title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            repeat(banners.size) {index ->
                val isSelected = pagerState.currentPage == index

                val width by animateDpAsState(
                    targetValue = if(isSelected) 24.dp else 8.dp,
                    label = "indicator_width"
                )

                val color by animateColorAsState(
                    targetValue =
                        if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    label = "indicator_color"
                )

                Box(
                    modifier = Modifier
                        .width(width)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(color)

                )
            }
        }
    }
}