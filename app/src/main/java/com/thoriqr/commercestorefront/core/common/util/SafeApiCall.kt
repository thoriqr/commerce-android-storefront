package com.thoriqr.commercestorefront.core.common.util

import com.thoriqr.commercestorefront.data.model.ApiResponse
import com.thoriqr.commercestorefront.data.model.ApiResponseWithMeta

suspend fun <T> safeApiCall(
    apiCall: suspend () -> ApiResponse<T>
): NetworkResult<T> {

    return try {
        val response = apiCall()

        if(response.success) {
            NetworkResult.Success(response.data!!)
        } else {
            NetworkResult.Error(
                response.error?.message ?: "Unknown error"
            )
        }
    } catch (e: Exception) {
        NetworkResult.Error(
            e.message ?: "Network error"
        )
    }
}

suspend fun <T, M> safeApiCallWithMeta(
    apiCall: suspend () -> ApiResponseWithMeta<T, M>
): NetworkResult<ApiResponseWithMeta<T, M>> {

    return try {
        val response = apiCall()

        if (response.success) {
            NetworkResult.Success(response)
        } else {
            NetworkResult.Error(
                response.error?.message ?: "Unknown error"
            )
        }

    } catch (e: Exception) {
        NetworkResult.Error(
            e.message ?: "Network error"
        )
    }
}