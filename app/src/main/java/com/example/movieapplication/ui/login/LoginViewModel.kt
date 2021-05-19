package com.example.movieapplication.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.ui.base.BaseViewModel
import com.example.movieapplication.utils.ext.toLoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class LoginViewModel @ViewModelInject constructor(private val apiRepository: ApiRepository) :
    BaseViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    fun loginUser() = liveData(Dispatchers.IO) {
        apiRepository.loginUser(email = userEmail.value ?: "", password = userPassword.value ?: "")
            .toLoadingState().collect {
                emit(it)
            }
    }
}