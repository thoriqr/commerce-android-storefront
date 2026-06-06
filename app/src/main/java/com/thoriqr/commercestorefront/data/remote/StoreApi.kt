package com.thoriqr.commercestorefront.data.remote

import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.data.model.ApiResponse
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApi {
    @GET("store/marketing/banners")
    suspend fun getBanners(
        @Query("placement") placement: String
    ): ApiResponse<List<BannerDto>>

    @GET("store/categories/popular")
    suspend fun getPopularCategories(): ApiResponse<List<PopularCategoryDto>>

    @GET("store/collections/preview")
    suspend fun getCollectionPreview(): ApiResponse<List<CollectionPreviewDto>>

    @GET("store/products/by-collection")
    suspend fun getProductsByCollection(
        @Query("slug") slug: String,
        @Query("cursor") cursor: String? = null
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>

    @GET("store/products/by-category")
    suspend fun getProductsByCategory(
        @Query("slugPath") slugPath: String,
        @Query("cursor") cursor: String? = null
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>

    @GET("store/products/by-search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("cursor") cursor: String? = null
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>
}