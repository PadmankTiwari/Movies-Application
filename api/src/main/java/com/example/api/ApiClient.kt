package com.example.api

import com.example.api.service.ApiService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClient(private val baseUrl: String) {

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Gson converter and sets [.okHttpClient]
     * as its client.
     *
     * @see .okHttpClient
     */
    private fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .client(okHttpClient()!!)
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see .setOkHttpClientDefaults
     */
    @Synchronized
    protected fun okHttpClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            setOkHttpClientDefaults(builder)
            builder.connectTimeout(2, TimeUnit.MINUTES)
            builder.callTimeout(2, TimeUnit.MINUTES)
            builder.readTimeout(2, TimeUnit.MINUTES)
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected open fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
        //override OkHttpClient client
    }

    /**
     * Return the current [Retrofit] instance. If none exists, builds a new one.
     * When building, sets the base url and a custom client with an [Interceptor] which supplies authentication
     * data.
     */
    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build()
        }
        return retrofit
    }

    fun apiService(): ApiService {
        return getRetrofit()!!.create(ApiService::class.java)
    }
}