package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCall
import com.thoriqr.commercestorefront.data.model.DimensionFilterDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getCategoryFilters(slugPath: String): NetworkResult<List<DimensionFilterDto>> {
        return safeApiCall {
            storeApi.getCategoryFilters(slugPath)
        }
    }

    suspend fun getSearchFilters(query: String): NetworkResult<List<DimensionFilterDto>> {
        return safeApiCall {
            storeApi.searchProductFilters(query)
        }
    }
}