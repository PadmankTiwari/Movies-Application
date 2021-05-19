package com.example.movieapplication.ui.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.database.entity.User
import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel @ViewModelInject constructor(private val apiRepository: ApiRepository) : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val userNumber = MutableLiveData<String>()
    val userProfession = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    fun saveUser() {
        viewModelScope.launch {
            apiRepository.addUserDB(User(
                userName = userName.value ?: "",
                userPassword = userPassword.value ?: "",
                userEmail = userEmail.value ?: "",
                userNumber = userNumber.value ?: "",
                userProfession = userProfession.value ?: ""
            ))
        }
    }

}