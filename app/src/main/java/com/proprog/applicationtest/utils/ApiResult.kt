package com.proprog.applicationtest.utils

data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResult<T> = ApiResult<T>(Status.SUCCESS, data, null)
        fun <T> loading(data: T): ApiResult<T> = ApiResult<T>(Status.LOADING, data, null)
        fun <T> error(data: T, message: String): ApiResult<T> = ApiResult<T>(Status.ERROR, data, message)
    }

    enum class Status {
        SUCCESS,ERROR,LOADING
    }
}
