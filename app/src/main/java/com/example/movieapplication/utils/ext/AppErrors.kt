package com.example.movieapplication.utils.ext

import androidx.annotation.StringRes
import com.example.api.exception.NoInternetConnection
import com.example.movieapplication.R
import com.example.movieapplication.data.model.AppError
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException


/**
 * Convert Throwable to AppError
 */
fun Throwable?.toAppError(): AppError? {
    return when (this) {
        null -> null
        is AppError -> this
        is NoInternetConnection -> AppError.ApiException.NoConnectivityException(this)
        is HttpException -> httpExceptionToAppError()
        is TimeoutCancellationException -> AppError.ApiException.NetworkException(this)
        else -> AppError.UnknownException(this)
    }
}

private fun HttpException.httpExceptionToAppError(): AppError {
    val errorResponse = response()
    return if (errorResponse != null) {
        val error = errorResponse.errorBody()?.string()
        if (error.isNullOrEmpty().not()) {
            try {
                AppError.ApiException.MagentoException(
                    AppError.ApiException.NetworkException(this)
                )
            } catch (e: Exception) {
                AppError.UnknownException(e)
            }
        } else {
            AppError.ApiException.NetworkException(this)
        }
    } else {
        AppError.ApiException.UnknownException(this)
    }
}

/**
 * Convert AppError to String Resources
 */
@StringRes
fun AppError.stringRes() = when (this) {
    is AppError.ApiException.NetworkException -> R.string.error_network
    is AppError.ApiException.NoConnectivityException -> R.string.error_no_internet_connection
    is AppError.ApiException.ServerException -> R.string.error_server
    is AppError.ApiException.SessionNotFoundException -> R.string.error_unknown
    is AppError.ApiException.UnknownException -> R.string.error_unknown
    is AppError.UnknownException -> R.string.error_unknown
    else -> R.string.error_unknown
}
