package com.thoriqr.commercestorefront.data.repository

import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.safeApiCall
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.remote.StoreApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionRepository @Inject constructor(
    private val storeApi: StoreApi
) {
    suspend fun getCollectionPreview(): NetworkResult<List<CollectionPreviewDto>> {
        return safeApiCall {
            storeApi.getCollectionPreview()
        }
    }
}