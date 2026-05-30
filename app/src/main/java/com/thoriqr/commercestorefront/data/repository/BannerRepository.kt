package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.NetworkResult
import com.thoriqr.commercestorefront.core.common.safeApiCall
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.BannerPlacement
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getBanners(placement: BannerPlacement): NetworkResult<List<BannerDto>> {
        return safeApiCall {
            storeApi.getBanners(placement = placement.value)
        }
    }
}