package com.example.movieapplication.utils.ext


import com.example.movieapplication.data.model.LoadState
import kotlinx.coroutines.flow.*


/**
 * wrap Flow with LoadState
 */
fun <T> Flow<T>.toLoadingState(): Flow<LoadState<T>> {
    return map<T, LoadState<T>> { LoadState.Loaded(it) }
        .onStart {
            @Suppress("UNCHECKED_CAST")
            emit(LoadState.Loading as LoadState<T>)
        }.onEach { LoadState.Loading }
        .catch { e ->
            emit(LoadState.Error<T>(e))
        }
}