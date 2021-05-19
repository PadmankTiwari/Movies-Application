package com.example.movieapplication.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.databinding.FragmentMenuBinding
import com.example.movieapplication.ui.base.BaseFragment
import com.example.movieapplication.utils.AppConstants
import com.example.movieapplication.utils.ext.stringRes
import com.example.movieapplication.utils.ext.toAppError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    private val menuViewModel: MenuViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        getUser()
    }

    private fun getUser() {
        menuViewModel.getUser().observe(viewLifecycleOwner, { loadState ->
            showLoading(loadState.isLoading)
            loadState.getErrorIfExists().toAppError()?.let { appError ->
                showMessage(appError.stringRes())
            }
            loadState.getValueOrNull()?.let { it ->
                getViewDataBinding().apply {
                    tvCompanyNameValue.text = AppConstants.COMPANY_NAME
                    tvAddressValue.text = AppConstants.COMPANY_ADDRESS
                    tvEmailValue.text = "XXXXXXXXXX${it.userEmail.substring(it.userEmail.lastIndexOf("@"))}"
                    tvPhoneValue.text = "XXXXXXXX${it.userNumber.takeLast(2)}"
                }
            }
        })
    }

    private fun onClick() {
        getViewDataBinding().clickListener = View.OnClickListener {
            when(it.id){
                R.id.btnLogout -> {
                    sessionManager.saveUserSession(false)
                    findNavController().navigate(R.id.action_menuFragment_to_landingFragment)
                    showMessage(getString(R.string.logout_successful))
                }
                R.id.ivBack -> findNavController().popBackStack()
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_menu

    override fun getBindingVariable() = BR.menuViewModel

    override fun getViewModel() = menuViewModel
}