package com.cybereast.tmdbapi.models


import com.cybereast.tmdbapi.myData.enums.NetworkStatus


class ApiResponse private constructor(
    val status: NetworkStatus,
    var t: Any?,
    val error: ErrorResponse?
) {

    companion object {
        fun loading(): ApiResponse {
            return ApiResponse(NetworkStatus.LOADING, null, null)
        }

        fun success(t: Any?): ApiResponse {
            return ApiResponse(NetworkStatus.SUCCESS, t, null)
        }

        fun error(error: ErrorResponse?): ApiResponse {
            return ApiResponse(NetworkStatus.ERROR, null, error)
        }

        fun complete(): ApiResponse {
            return ApiResponse(NetworkStatus.COMPLETED, null, null)
        }

        fun expire(error: ErrorResponse?): ApiResponse {
            return ApiResponse(NetworkStatus.EXPIRE, null, error)
        }
    }

}