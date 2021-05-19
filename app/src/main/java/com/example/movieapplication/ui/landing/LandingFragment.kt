package com.example.movieapplication.ui.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.R
import com.example.movieapplication.BR
import com.example.movieapplication.databinding.FragmentLandingBinding
import com.example.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : BaseFragment<FragmentLandingBinding, LandingViewModel>() {

    private val landingViewModel: LandingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        getViewDataBinding().clickListener = View.OnClickListener {
            when(it.id){
                R.id.btnLogin -> findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
                R.id.btnSignUp -> findNavController().navigate(R.id.action_landingFragment_to_signUpFragment)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_landing

    override fun getBindingVariable() = BR.landingViewModel

    override fun getViewModel() = landingViewModel
}