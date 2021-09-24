package com.proprog.applicationtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = bindLayout()

        setContentView(binding.root)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(getViewModelClass())
    }

    abstract fun bindLayout(): VB

    abstract fun getViewModelClass(): Class<VM>

}