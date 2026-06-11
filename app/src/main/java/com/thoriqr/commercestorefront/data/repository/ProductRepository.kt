package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCall
import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getProduct(id: Int): NetworkResult<ProductDetailDto> {
        return safeApiCall {
            storeApi.getProduct(id)
        }
    }

    suspend fun getProductVariant(productId: Int, variantId: Int): NetworkResult<VariantDetailDto> {
        return safeApiCall {
            storeApi.getProductVariant(productId, variantId)
        }
    }
}