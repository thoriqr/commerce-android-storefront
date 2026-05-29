package com.thoriqr.commercestorefront.core.common

import com.thoriqr.commercestorefront.data.model.ApiResponse

suspend fun <T> safeApiCall(
    apiCall: suspend () -> ApiResponse<T>
): NetworkResult<T>{

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