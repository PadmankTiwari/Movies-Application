package com.example.movieapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.model.response.MovieListResponse
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.ui.base.BaseFragment
import com.example.movieapplication.utils.ext.stringRes
import com.example.movieapplication.utils.ext.toAppError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager.saveUserSession(true)
        callMovieListApi()
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_menuFragment)
        }
    }

    private fun callMovieListApi() {
        showLoading(true)
        homeViewModel.getMovieList().observe(viewLifecycleOwner, { loadState ->
            showLoading(loadState.isLoading)
            loadState.getErrorIfExists().toAppError()?.let { appError ->
                showMessage(appError.stringRes())
            }
            loadState.getValueOrNull()?.let { response ->
                hideProgressBar()
                initAdapter(response)
            }
        })
    }

    private fun initAdapter(movieListResponse: MovieListResponse) {
        val movieListAdapter = MovieListAdapter(movieListResponse = movieListResponse.result)
        getViewDataBinding().rvMoviesList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieListAdapter
        }
    }


    override fun getLayoutId() = R.layout.fragment_home

    override fun getBindingVariable() = BR.homeViewModel

    override fun getViewModel() = homeViewModel
}