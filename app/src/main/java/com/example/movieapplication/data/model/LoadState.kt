package com.example.movieapplication.data.model

import androidx.annotation.Keep


/**
 * @Details sealed class for to manage loading state
 */
@Keep
interface ErrorGettable {
    fun getErrorIfExists(): Throwable?
}

@Keep
sealed class LoadState<out T> : ErrorGettable {
    object Loading : LoadState<Nothing>()
    class Loaded<T>(val value: T) : LoadState<T>()
    class Error<T>(val e: Throwable) : LoadState<T>()

    val isLoading get() = this is Loading
    override fun getErrorIfExists() = if (this is Error) e else null
    fun getValueOrNull(): T? = if (this is Loaded<T>) value else null
}

@Keep
sealed class LoadingState : ErrorGettable {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    class Error(val e: Throwable) : LoadingState()

    val isLoading get() = this is Loading
    override fun getErrorIfExists() = if (this is Error) e else null
}

@Keep
sealed class ResultState<T> : ErrorGettable {
    class Success<T>(val value: T) : ResultState<T>()
    class Error<T>(val e: Throwable) : ResultState<T>()

    override fun getErrorIfExists() = if (this is Error) e else null
    fun getOrDefault(default: T): T {
        if (this is Success) return value
        return default
    }
}

@Keep
fun List<ErrorGettable>.firstErrorOrNull(): Throwable? {
    return mapNotNull { it.getErrorIfExists() }.firstOrNull()
}
