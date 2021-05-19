package com.example.movieapplication.database

import com.example.movieapplication.database.dao.UserDao
import com.example.movieapplication.database.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Details implementation class for databases
 */
class UserDatabaseImpl @Inject constructor(
    private val database: Database,
    private val userDao: UserDao
) : UserDatabase {

    override suspend fun insertUser(user: User) {
        return userDao.insert(user)
    }

    override suspend fun loginUser(email: String, password: String) =
        userDao.loginUser(email = email, password = password)

    override suspend fun getUser(email: String) = userDao.getUser(email = email)
}