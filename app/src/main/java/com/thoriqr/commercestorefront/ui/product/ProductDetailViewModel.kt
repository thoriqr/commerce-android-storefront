package com.thoriqr.commercestorefront.ui.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.thoriqr.commercestorefront.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val productId =
        checkNotNull(
            savedStateHandle.get<Int>("productId")
        )
}