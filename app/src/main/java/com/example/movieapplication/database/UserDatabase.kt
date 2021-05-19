package com.example.movieapplication.database

import com.example.movieapplication.database.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * @Details users database interface
 */
interface UserDatabase {

    suspend fun insertUser(user: User)

    suspend fun loginUser(email: String, password: String): Flow<Int>

    suspend fun getUser(email: String): Flow<User>
}