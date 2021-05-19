package com.example.movieapplication.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["name","email","password"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "name")
    var userName: String,
    @ColumnInfo(name = "password")
    var userPassword: String,
    @ColumnInfo(name = "email")
    var userEmail: String,
    @ColumnInfo(name = "phone_number")
    var userNumber: String,
    @ColumnInfo(name = "profession")
    val userProfession: String
) : BaseEntity