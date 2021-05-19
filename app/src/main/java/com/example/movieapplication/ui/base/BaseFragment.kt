package com.example.movieapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.movieapplication.utils.autoCleared
import java.util.regex.Pattern

/**
 * @Details Base class for fragments
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private var mViewDataBinding by autoCleared<T>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false).also {
        mViewDataBinding = it
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewDataBinding.executePendingBindings()
    }.root

    fun showMessage(message: String) {
        (requireActivity() as BaseActivity).showMessage(message)
    }

    fun showMessage(stringRes: Int) {
        (requireActivity() as BaseActivity).showMessage(stringRes)
    }

    fun showLoading(visible: Boolean) {
        if (visible)
            (requireActivity() as BaseActivity).showLoading(visible)
        else
            (requireActivity() as BaseActivity).hideLoading()
    }

    fun hideProgressBar() {
        (requireActivity() as BaseActivity).hideLoading()
    }

    /**
     * Method for validation of email
     */
    fun emailValidator(message: String): Boolean {
        val p = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                    ")+"
        )
        val m = p.matcher(message)
        return m.matches()
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * @return binding variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return view model instance
     */
    abstract fun getViewModel(): V

    /**
     * Method for get View data binding
     */
    fun getViewDataBinding(): T {
        return mViewDataBinding
    }
}