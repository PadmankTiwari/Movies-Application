package com.example.movieapplication.ui.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapplication.R
import com.example.movieapplication.utils.ext.showLoadingDialog
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    fun showMessage(stringRes: Int) {
        Snackbar.make(findViewById(R.id.clMain), stringRes, Snackbar.LENGTH_LONG).show()
    }

    fun showMessage(message: String) {
        Snackbar.make(findViewById(R.id.clMain), message, Snackbar.LENGTH_LONG).show()
    }

    fun showLoading(visible: Boolean) {
        if (visible) {
            hideLoading()
            progressDialog = showLoadingDialog(this)
        } else {
            hideLoading()
        }
    }

    fun hideLoading() {
        progressDialog?.cancel()
    }

}