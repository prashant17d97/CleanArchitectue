package com.prashant.cleanarchitecture.utils

import retrofit2.Response

object NetworkUtils {

    fun <T> Result<T>.toNetworkResponse(): NetworkResponse<T> {
        return try {
            return when {
                isSuccess -> {
                    // If the result is successful, map it to a Success response.
                    NetworkResponse.Success(getOrNull(), responseMessage = null)
                }

                isFailure -> {
                    // If the result failed, map it to an Error response.
                    val exception = exceptionOrNull()
                    val errorMessage = exception?.message ?: "Unknown Error"
                    val statusCode =
                        400  // Default or you can get it from the exception if applicable

                    NetworkResponse.Error(errorMessage = errorMessage, statusCode = statusCode)
                }

                else -> {
                    // For any unexpected scenario, return a default error
                    NetworkResponse.Error(errorMessage = "Unknown Error", statusCode = 400)
                }
            }
        } catch (exception: Exception) {
            NetworkResponse.Error(
                errorMessage = "Unknown Error: ${exception.localizedMessage}",
                statusCode = 400
            )

        }
    }

    fun <T> Response<T>.toNetworkResponse(): NetworkResponse<T> {
        return try {
            when {
                isSuccessful -> {
                    // If response is successful, return the body
                    NetworkResponse.Success(body(), responseMessage = message())
                }

                else -> {
                    // If response failed, extract error message and status code
                    val errorMessage = errorBody()?.string() ?: "Unknown Error"
                    NetworkResponse.Error(errorMessage = errorMessage, statusCode = code())
                }
            }
        } catch (exception: Exception) {
            NetworkResponse.Error(
                errorMessage = "Unknown Error: ${exception.localizedMessage}",
                statusCode = 500
            )
        }
    }


//    fun <T> okhttp3.Response<T>.toNetworkResponse(): NetworkResponse<T> {
//        return try {
//            when {
//                isSuccessful -> {
//                    // If response is successful, return the body
//                    NetworkResponse.Success(body(), responseMessage = message())
//                }
//
//                else -> {
//                    // If response failed, extract error message and status code
//                    val errorMessage = errorBody()?.string() ?: "Unknown Error"
//                    NetworkResponse.Error(errorMessage = errorMessage, statusCode = code())
//                }
//            }
//        } catch (exception: Exception) {
//            NetworkResponse.Error(
//                errorMessage = "Unknown Error: ${exception.localizedMessage}",
//                statusCode = 500
//            )
//        }
//    }

}