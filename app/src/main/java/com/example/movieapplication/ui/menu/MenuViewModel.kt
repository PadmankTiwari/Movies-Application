package com.example.movieapplication.ui.menu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.ui.base.BaseViewModel
import com.example.movieapplication.utils.ext.toLoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class MenuViewModel @ViewModelInject constructor(private val apiRepository: ApiRepository) :
    BaseViewModel() {

    fun getUser() = liveData(Dispatchers.IO) {
        apiRepository.getUser().toLoadingState().collect {
            emit(it)
        }
    }
}