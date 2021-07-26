package com.example.rawggamesapp.util

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)

    object Loading : Resource<Nothing>()

}