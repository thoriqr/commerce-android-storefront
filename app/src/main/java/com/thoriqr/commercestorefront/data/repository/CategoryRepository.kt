package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCall
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