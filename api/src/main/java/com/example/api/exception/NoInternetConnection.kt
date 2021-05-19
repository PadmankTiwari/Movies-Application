package com.example.api.exception

import com.example.api.ApiConstants
import java.io.IOException

class NoInternetConnection : IOException() {
    override val message: String
    get() = ApiConstants.NO_INTERNET_MSG
}