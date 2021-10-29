package com.proprog.applicationtest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.proprog.applicationtest.data.repository.StackDataSource
import com.proprog.applicationtest.data.repository.StackRepository
import com.proprog.applicationtest.utils.ApiResult
import kotlinx.coroutines.Dispatchers

private const val NETWORK_PAGE_SIZE = 10

class StackViewModel : ViewModel() {
    // val stackLiveData: MutableLiveData<StackResponse> = MutableLiveData()
    private val stackRepository = StackRepository()
    fun getStackAnswers(page: String, pageSize: String, site: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(null))
        //  kotlinx.coroutines.delay(4000)
        try {
            emit(ApiResult.success(stackRepository.getStackAnswers(page, pageSize, site)))
        } catch (e: Exception) {
            emit(ApiResult.error(null, e.message ?: "Something went wrong"))
        }
    }

    fun getStackAnswers(query: String) =
        Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
            StackDataSource(stackRepository, query)
        }.flow.cachedIn(viewModelScope)
}