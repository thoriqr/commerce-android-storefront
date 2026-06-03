package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.NetworkResult
import com.thoriqr.commercestorefront.core.common.safeApiCall
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getPopularCategories(): NetworkResult<List<PopularCategoryDto>> {
        return safeApiCall {
            storeApi.getPopularCategories()
        }
    }
}