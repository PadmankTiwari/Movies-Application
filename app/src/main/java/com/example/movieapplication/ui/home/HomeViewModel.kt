package com.example.movieapplication.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.example.api.ApiConstants
import com.example.api.model.request.MovieListRequest
import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.ui.base.BaseViewModel
import com.example.movieapplication.utils.ext.toLoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class HomeViewModel @ViewModelInject constructor(private val apiRepository: ApiRepository) :
    BaseViewModel() {

    fun getMovieList() = liveData(Dispatchers.IO) {
        apiRepository.getMoviesList(MovieListRequest(
            category = ApiConstants.CATEGORY,
            genre = ApiConstants.GENRE,
            language = ApiConstants.LANGUAGE,
            sort = ApiConstants.SORT
        )).toLoadingState().collect {
            emit(it)
        }
    }
}