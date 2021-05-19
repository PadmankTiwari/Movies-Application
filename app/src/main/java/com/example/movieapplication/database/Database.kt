package com.example.movieapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapplication.database.dao.UserDao
import com.example.movieapplication.database.entity.User

@Database(entities = [User::class], version = 5)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}