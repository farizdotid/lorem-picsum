package com.app.lorempiscum.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.lorempiscum.utils.ProgressDialogUtil

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseView,
    AppCompatActivity() {

    protected lateinit var viewModel: VM

    private lateinit var binding: DB

    protected abstract fun getViewModel(): Class<VM>

    private lateinit var dialog: AlertDialog

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        viewModel = ViewModelProvider(this).get(getViewModel())
        dialog = ProgressDialogUtil.setProgressDialog(this, "Loading...")
    }

    override fun getContext(): Context {
        return this
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    open fun showHideLoading() {
        viewModel.loadingStatus.observe(this, Observer { status ->
            if (status) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }
}