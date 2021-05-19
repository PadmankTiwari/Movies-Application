package com.example.movieapplication.data.session

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * @Details implementation of session manager
 */
class SessionManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : SessionManager {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor = prefs.edit()

    companion object {
        const val PREF_NAME = "user_session"
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
    }

    override fun saveUserSession(token: Boolean) {
        editor.putBoolean(USER_TOKEN, token)
        editor.apply()
    }

    override fun fetchUserSession(): Boolean {
        return prefs.getBoolean(USER_TOKEN, false)
    }

    override fun saveUserid(id: String) {
        editor.putString(USER_ID, id)
        editor.apply()
    }

    override fun fetchUserid(): String {
        return prefs.getString(USER_ID, "").toString()
    }
}