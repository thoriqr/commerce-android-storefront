package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCallWithMeta
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import com.thoriqr.commercestorefront.ui.listing.ProductListingQuery
import com.thoriqr.commercestorefront.ui.listing.toQueryMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductListingRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getByCollection(
        slug: String,
        filters: ProductListingQuery
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.getProductsByCollection(
                slug = slug,
                filters = filters.toQueryMap()
            )
        }
    }

    suspend fun getByCategory(
        slugPath: String,
        filters: ProductListingQuery
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.getProductsByCategory(
                slugPath = slugPath,
                filters = filters.toQueryMap()
            )
        }
    }

    suspend fun getBySearch(
        search: String,
        filters: ProductListingQuery
    ): NetworkResult<ApiResponseWithMeta<List<ProductCardDto>, CursorMetaDto>> {

        return safeApiCallWithMeta {
            storeApi.searchProducts(
                search = search,
                filters = filters.toQueryMap()
            )
        }
    }
}