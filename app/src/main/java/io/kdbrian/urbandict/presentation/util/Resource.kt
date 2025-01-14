package io.kdbrian.urbandict.presentation.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false,
) {

    class Success<T>(data: T?) : Resource<T>(data = data, isLoading = false)

    class Error<T>(message: String?, data: T? = null) :
        Resource<T>(data = data, message = message, isLoading = false)

    class Loading<T> : Resource<T>(isLoading = true)

}