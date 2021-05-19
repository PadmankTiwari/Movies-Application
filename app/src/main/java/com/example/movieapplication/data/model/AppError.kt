package com.example.movieapplication.data.model

import androidx.annotation.Keep

/**
 * @Details sealed class for application error
 */
@Keep
sealed class AppError : RuntimeException {
    constructor()
    constructor(cause: Throwable?) : super(cause)

    sealed class ApiException(cause: Throwable?) : AppError(cause) {
        class NetworkException(cause: Throwable?) : ApiException(cause)
        class ServerException(cause: Throwable?) : ApiException(cause)
        class SessionNotFoundException(cause: Throwable?) : AppError(cause)
        class UnknownException(cause: Throwable?) : AppError(cause)
        class MagentoException(cause: Throwable?) : AppError(cause)
        class NoConnectivityException(cause: Throwable?) : AppError(cause)
    }

    sealed class ExternalIntegrationError(cause: Throwable?) : AppError(cause) {
        class NoEmailFoundException(cause: Throwable?) : ExternalIntegrationError(cause)
    }

    class UnknownException(cause: Throwable?) : AppError(cause)
}
