package com.example.movieapplication.repos.repositoryImpl

import com.example.api.model.request.MovieListRequest
import com.example.api.service.ApiService
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.database.UserDatabase
import com.example.movieapplication.database.entity.User
import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.utils.ext.bodyOrThrow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDB: UserDatabase,
    private val sessionManager: SessionManager
) : ApiRepository {

    override suspend fun getMoviesList(movieListRequest: MovieListRequest) = flow {
        val movieListResponse = apiService.getMoviesList(movieListRequest = movieListRequest)
            .execute()
            .bodyOrThrow()
        emit(movieListResponse)
    }

    override suspend fun addUserDB(user: User) {
        sessionManager.saveUserid(user.userEmail)
        return userDB.insertUser(user = user)
    }

    override suspend fun loginUser(email: String, password: String) =
        userDB.loginUser(email = email, password = password)

    override suspend fun getUser() = userDB.getUser(email = sessionManager.fetchUserid())
}