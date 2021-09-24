package com.proprog.applicationtest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proprog.applicationtest.data.repository.StackRepository

class StackViewModelFactory(val stackRepository: StackRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StackViewModel::class.java)) {
            return StackViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}