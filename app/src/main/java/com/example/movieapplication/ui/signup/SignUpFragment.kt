package com.example.movieapplication.ui.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.databinding.FragmentSignUpBinding
import com.example.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_dropdown_list,
            resources.getStringArray(R.array.Profession)
        )
        getViewDataBinding().etUserProfession.setAdapter(adapter)
        getViewDataBinding().etUserProfession.setOnItemClickListener { _, _, i, _ ->
            signUpViewModel.userProfession.value = resources.getStringArray(R.array.Profession)[i]
        }
        onClick()
    }

    private fun onClick() {
        getViewDataBinding().clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnSave -> {
                    if (isValidData()) {
                        signUpViewModel.saveUser()
                        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                        showMessage(getString(R.string.sign_up_successful))
                    }
                }
            }
        }
    }

    private fun isValidData(): Boolean {
        var isValid = true
        clearError()
        when {
            TextUtils.isEmpty(signUpViewModel.userName.value) -> {
                getViewDataBinding().tilUserName.error = getString(R.string.username_empty_error)
                isValid = false
            }
            TextUtils.isEmpty(signUpViewModel.userNumber.value) -> {
                getViewDataBinding().tilUserNumber.error =
                    getString(R.string.user_number_empty_error)
                isValid = false
            }
            (signUpViewModel.userNumber.value.toString().length == 10).not() -> {
                getViewDataBinding().tilUserNumber.error =
                    getString(R.string.user_number_length_error)
                isValid = false
            }
            TextUtils.isEmpty(signUpViewModel.userProfession.value) -> {
                getViewDataBinding().tilUserProfession.error =
                    getString(R.string.user_profession_empty_error)
                isValid = false
            }
            TextUtils.isEmpty(signUpViewModel.userEmail.value) -> {
                getViewDataBinding().tilUserEmail.error = getString(R.string.user_email_empty_error)
                isValid = false
            }
            emailValidator(signUpViewModel.userEmail.value.toString()).not() -> {
                getViewDataBinding().tilUserEmail.error =
                    getString(R.string.user_email_incorrect_error)
                isValid = false
            }
            TextUtils.isEmpty(signUpViewModel.userPassword.value) -> {
                getViewDataBinding().tilUserPassword.error =
                    getString(R.string.user_password_empty_error)
                isValid = false
            }
        }
        return isValid
    }

    private fun clearError() {
        getViewDataBinding().tilUserName.isErrorEnabled = false
        getViewDataBinding().tilUserNumber.isErrorEnabled = false
        getViewDataBinding().tilUserEmail.isErrorEnabled = false
        getViewDataBinding().tilUserProfession.isErrorEnabled = false
        getViewDataBinding().tilUserPassword.isErrorEnabled = false
    }

    override fun getLayoutId() = R.layout.fragment_sign_up

    override fun getBindingVariable() = BR.signUpViewModel

    override fun getViewModel() = signUpViewModel
}