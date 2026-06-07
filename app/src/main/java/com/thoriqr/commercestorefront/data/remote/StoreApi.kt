package com.thoriqr.commercestorefront.data.remote

import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.data.model.ApiResponse
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.CategoryDetailDto
import com.thoriqr.commercestorefront.data.model.CollectionDetailDto
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreApi {
    @GET("store/marketing/banners")
    suspend fun getBanners(
        @Query("placement") placement: String
    ): ApiResponse<List<BannerDto>>

    @GET("store/categories/popular")
    suspend fun getPopularCategories(): ApiResponse<List<PopularCategoryDto>>

    @GET("store/categories/detail")
    suspend fun getCategoryDetail(
        @Query("slugPath") slugPath: String
    ): ApiResponse<CategoryDetailDto>

    @GET("store/collections/preview")
    suspend fun getCollectionPreview(): ApiResponse<List<CollectionPreviewDto>>

    @GET("store/collections/{slug}")
    suspend fun getCollectionDetail(
        @Path("slug") slug: String
    ): ApiResponse<CollectionDetailDto>

    @GET("store/categories/filters")
    suspend fun getCategoryFilters(
        @Query("slugPath") slugPath: String
    ): ApiResponse<List<DimensionFilterDto>>

    @GET("store/products/filters")
    suspend fun searchProductFilters(
        @Query("q") query: String
    ): ApiResponse<List<DimensionFilterDto>>

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