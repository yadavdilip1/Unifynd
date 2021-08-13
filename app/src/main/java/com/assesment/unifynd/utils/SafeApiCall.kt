package com.assesment.unifynd.utils


interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        }
        catch (throwable: Throwable) {
            Resource.Failure("Something went wrong")
        }
    }
}