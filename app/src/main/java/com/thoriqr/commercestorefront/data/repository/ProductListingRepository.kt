package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCallWithMeta
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductListingRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getByCollection(
        slug: String,
        cursor: String?
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.getProductsByCollection(
                slug = slug,
                cursor = cursor
            )
        }
    }

    suspend fun getByCategory(
        slugPath: String,
        cursor: String?
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.getProductsByCategory(
                slugPath = slugPath,
                cursor = cursor
            )
        }
    }

    suspend fun getBySearch(
        query: String,
        cursor: String?
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.searchProducts(
                query = query,
                cursor = cursor
            )
        }
    }
}