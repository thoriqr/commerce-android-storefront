package com.thoriqr.commercestorefront.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoriqr.commercestorefront.ui.components.FullPageError
import com.thoriqr.commercestorefront.ui.product.section.ProductDescriptionSection
import com.thoriqr.commercestorefront.ui.product.section.ProductImageCarousel
import com.thoriqr.commercestorefront.ui.product.section.ProductInfoSection
import com.thoriqr.commercestorefront.ui.product.section.ProductVariantSection

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            FullPageError(
                title = "Unable to load product",
                message = uiState.error ?: "Something went wrong!",
                actionLabel = "Retry",
                onActionClick = viewModel::retry
            )
        }

        uiState.product != null &&
                uiState.selectedVariant != null -> {

            Scaffold (

                bottomBar = {

                    ProductBottomBar (
                        variant = uiState.selectedVariant,
                        isVariantLoading = uiState.isVariantLoading,
                        onAddToCartClick = {

                        }
                    )
                }

            ) { innerPadding ->

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp
                    )
                ) {

                    item {
                        ProductImageCarousel(
                            images = uiState.product!!.images,
                            selectedImageIndex = uiState.selectedImageIndex
                        )
                    }

                    item {
                        ProductInfoSection(
                            product = uiState.product!!,
                            variant = uiState.selectedVariant!!,
                            isVariantLoading = uiState.isVariantLoading
                        )
                    }

                    item {
                        ProductVariantSection(
                            product = uiState.product!!,
                            selectedVariantId = uiState.selectedVariantId,
                            selectedOptions = uiState.selectedOptions,
                            isLoading = uiState.isVariantLoading,
                            onOptionSelected = { dimensionKey, valueKey ->
                                viewModel.selectOption(
                                    dimensionKey = dimensionKey,
                                    valueKey = valueKey
                                )
                            }
                        )
                    }

                    item {
                        ProductDescriptionSection(
                            description = uiState.product!!.description
                        )
                    }
                }
            }
        }
    }
}