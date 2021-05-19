package com.example.movieapplication.repos.repository


import com.example.api.model.request.MovieListRequest
import com.example.api.model.response.MovieListResponse
import com.example.movieapplication.database.entity.User
import kotlinx.coroutines.flow.Flow


interface ApiRepository {
    suspend fun getMoviesList(movieListRequest: MovieListRequest): Flow<MovieListResponse>

    suspend fun addUserDB(user: User)

    suspend fun loginUser(email: String, password: String) : Flow<Int>

    suspend fun getUser() : Flow<User>
}