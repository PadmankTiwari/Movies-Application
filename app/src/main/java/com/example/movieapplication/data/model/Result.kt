package com.example.movieapplication.data.model

import androidx.annotation.Keep

/**
 * @Details Result sealed class to handle retrofit api response
 */
@Keep
sealed class Result<T> {
    open fun get(): T? = null

    fun getOrThrow(): T = when (this) {
        is Success -> get()
        is ErrorResult -> throw throwable
    }
}

@Keep
data class Success<T>(val data: T, val responseModified: Boolean = true) : Result<T>() {
    override fun get(): T = data
}

@Keep
data class ErrorResult<T>(val throwable: Throwable) : Result<T>()
