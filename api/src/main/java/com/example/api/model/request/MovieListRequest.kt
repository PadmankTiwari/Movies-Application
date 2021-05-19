package com.example.api.model.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieListRequest(
    @SerializedName("category")
    val category: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("sort")
    val sort: String
)