package com.example.movieapplication.data.session

interface SessionManager {

    fun saveUserSession(token: Boolean)
    fun fetchUserSession(): Boolean

    fun saveUserid(id: String)
    fun fetchUserid(): String
}