package com.example.api.service

import com.example.api.ApiConstants
import com.example.api.model.request.MovieListRequest
import com.example.api.model.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(ApiConstants.POST_MOVIE_LIST)
    fun getMoviesList(@Body movieListRequest: MovieListRequest) : Call<MovieListResponse>
}