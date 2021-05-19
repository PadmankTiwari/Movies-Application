package com.example.movieapplication.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.databinding.FragmentSplashBinding
import com.example.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    private val splashViewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToMain()
    }

    private fun navigateToMain() {
        lifecycleScope.launchWhenResumed {
            val startFragmentId = when {
                sessionManager.fetchUserSession() -> {
                    R.id.action_splashFragment_to_homeFragment
                }
                else -> {
                    R.id.action_splashFragment_to_landingFragment
                }
            }
            findNavController().navigate(startFragmentId)
        }
    }

    override fun getLayoutId() = R.layout.fragment_splash

    override fun getBindingVariable() = BR.splashViewModel

    override fun getViewModel() = splashViewModel
}