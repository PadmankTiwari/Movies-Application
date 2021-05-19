package com.example.movieapplication.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.databinding.FragmentLoginBinding
import com.example.movieapplication.ui.base.BaseFragment
import com.example.movieapplication.utils.ext.stringRes
import com.example.movieapplication.utils.ext.toAppError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        getViewDataBinding().clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnLogin -> {
                    if (isValidData()) {
                        loginViewModel.loginUser().observe(viewLifecycleOwner, { loadState ->
                            showLoading(loadState.isLoading)
                            loadState.getErrorIfExists().toAppError()?.let { appError ->
                                showMessage(appError.stringRes())
                            }
                            loadState.getValueOrNull()?.let { response ->
                                if (response == 1) {
                                    sessionManager.saveUserid(loginViewModel.userEmail.value.toString())
                                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                                else
                                    showMessage(getString(R.string.invalid_credential))
                            }
                        })
                    }
                }
            }
        }
    }

    private fun isValidData(): Boolean {
        var isValid = true
        clearError()
        when {
            TextUtils.isEmpty(loginViewModel.userEmail.value) -> {
                getViewDataBinding().tilUserEmail.error = getString(R.string.user_email_empty_error)
                isValid = false
            }
            emailValidator(loginViewModel.userEmail.value.toString()).not() -> {
                getViewDataBinding().tilUserEmail.error =
                    getString(R.string.user_email_incorrect_error)
                isValid = false
            }
            TextUtils.isEmpty(loginViewModel.userPassword.value) -> {
                getViewDataBinding().tilUserPassword.error =
                    getString(R.string.user_password_empty_error)
                isValid = false
            }
        }
        return isValid
    }

    private fun clearError() {
        getViewDataBinding().tilUserEmail.isErrorEnabled = false
        getViewDataBinding().tilUserPassword.isErrorEnabled = false
    }

    override fun getLayoutId() = R.layout.fragment_login

    override fun getBindingVariable() = BR.loginViewModel

    override fun getViewModel() = loginViewModel
}