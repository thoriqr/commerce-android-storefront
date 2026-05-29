package com.thoriqr.commercestorefront.data.remote

import com.thoriqr.commercestorefront.data.model.ApiResponse
import com.thoriqr.commercestorefront.data.model.BannerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApi {
    @GET("store/marketing/banners")
    suspend fun getBanners(
        @Query("placement") placement: String
    ): ApiResponse<List<BannerDto>>
}