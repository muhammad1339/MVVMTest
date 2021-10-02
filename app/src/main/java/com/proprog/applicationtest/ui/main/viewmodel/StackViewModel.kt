package com.proprog.applicationtest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.proprog.applicationtest.data.repository.StackRepository
import com.proprog.applicationtest.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StackViewModel : ViewModel() {
    // val stackLiveData: MutableLiveData<StackResponse> = MutableLiveData()
    private val stackRepository = StackRepository()
    fun getStackAnswers(page: String, pageSize: String, site: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(null))
        try {
            emit(ApiResult.success(stackRepository.getStackAnswers(page, pageSize, site)))
        } catch (e: Exception) {
            emit(ApiResult.error(null, e.message ?: "Something went wrong"))
        }
    }

    fun get(page: String, pageSize: String, site: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(null))
        try {
            emit(ApiResult.success(stackRepository.getStackAnswers(page, pageSize, site)))
        } catch (e: Exception) {
            emit(ApiResult.error(null, e.message ?: "Something went wrong"))
        }
    }

}