package com.example.movieapplication.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.movieapplication.database.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<User> {

    @Query("SELECT COUNT(id) FROM user where email= :email and password= :password")
    abstract fun loginUser(email: String, password: String): Flow<Int>

    @Query("SELECT * FROM user where email= :email")
    abstract fun getUser(email: String): Flow<User>
}