package com.prashant.cleanarchitecture.utils

import com.prashant.cleanarchitecture.utils.NetworkResponse.EmptyBodySuccess
import com.prashant.cleanarchitecture.utils.NetworkResponse.Error
import com.prashant.cleanarchitecture.utils.NetworkResponse.Loading
import com.prashant.cleanarchitecture.utils.NetworkResponse.Success
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response structure for API calls, providing different states based on the response status.
 * Uses Kotlinx.serialization annotations for JSON serialization.
 * @param Generic The type of data contained in the response.
 * @property Loading Represents the initial state when no API call has been made.
 * @property Success Represents a successful API call with data.
 * @property EmptyBodySuccess Represents a successful API call with an empty body.
 * @property Error Represents an error state in API call.
 *
 * @author Prashant Singh
 */
@Serializable
sealed class NetworkResponse<out Generic> {
    // Represents the initial state when no API call has been made.
    @Serializable
    @SerialName("idle")
    data object Loading : NetworkResponse<Nothing>()

    /**
     * Represents a successful API call with data.
     * @param response The response data of type [Generic].
     * @param responseMessage A message accompanying the response, if any.
     * @param statusCode The HTTP status code of the response.
     * */
    @Serializable
    @SerialName("success")
    data class Success<Generic>(
        val response: Generic?,
        val responseMessage: String? = null,
        val statusCode: Int? = null
    ) : NetworkResponse<Generic>()

    // Represents a successful API call with an empty body.
    @Serializable
    @SerialName("emptyBodySuccess")
    data class EmptyBodySuccess(
        val responseMessage: String?,
        val statusCode: Int?
    ) : NetworkResponse<String>()

    /**
     *  Represents an error state in API call.
     * @param errorMessage The error message accompanying the error state.
     * @param statusCode The HTTP status code of the error response.
     */
    @Serializable
    @SerialName("error")
    data class Error(
        val errorMessage: String?,
        val statusCode: Int?
    ) : NetworkResponse<Nothing>()


    /**
     * Parses a response and executes the corresponding callback based on the response type.
     *
     * @param loading A function that takes a Boolean indicating whether the loading state should be shown.
     * @param success A function that takes the successful response (Generic?) and the status code (Int?).
     * @param error A function that takes the error message (String?) and the status code (Int?).
     */
    inline fun parseResponse(
        loading: (Boolean) -> Unit,
        success: (Generic?, responseMessage: String?, Int?) -> Unit,
        error: (String?, Int?) -> Unit
    ) {
        // Show loading indicator only if the response is Idle (request in progress)


        val loadingResponse=when (this) {
            is EmptyBodySuccess -> {
                error(responseMessage, statusCode)
                false
            }

            is Error -> {
                error(errorMessage, statusCode)
                false
            }

            is Success<Generic> -> {
                success(response, responseMessage, statusCode)
                false
            }

            is Loading -> {
                true
            }
        }

        loading(loadingResponse)
    }
}
