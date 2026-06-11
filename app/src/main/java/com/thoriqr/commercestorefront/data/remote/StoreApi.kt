package com.thoriqr.commercestorefront.data.remote

import com.thoriqr.commercestorefront.core.common.model.CursorMetaDto
import com.thoriqr.commercestorefront.data.model.ApiResponse
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.CategoryDetailDto
import com.thoriqr.commercestorefront.data.model.CategoryTreeDto
import com.thoriqr.commercestorefront.data.model.CollectionDetailDto
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.data.model.ProductCardDto
import com.thoriqr.commercestorefront.data.model.ProductDetailDto
import com.thoriqr.commercestorefront.data.model.VariantDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

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

    @GET("store/categories/mega-menu")
    suspend fun getCategoryTree(): ApiResponse<List<CategoryTreeDto>>

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
        @QueryMap filters: Map<String, String>
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>

    @GET("store/products/by-category")
    suspend fun getProductsByCategory(
        @Query("slugPath") slugPath: String,
        @QueryMap filters: Map<String, String>
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>

    @GET("store/products/by-search")
    suspend fun searchProducts(
        @Query("q") search: String,
        @QueryMap filters: Map<String, String>
    ): ApiResponseWithMeta<
            List<ProductCardDto>, CursorMetaDto>

    @GET("store/products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): ApiResponse<ProductDetailDto>

    @GET("store/products/{productId}/variants/{variantId}")
    suspend fun getProductVariant(
        @Path("productId") productId: Int,
        @Path("variantId") variantId: Int
    ): ApiResponse<VariantDetailDto>

}