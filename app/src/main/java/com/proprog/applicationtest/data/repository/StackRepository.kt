package com.proprog.applicationtest.data.repository

import com.proprog.applicationtest.data.api.ApiClient
import com.proprog.applicationtest.data.model.StackResponse
import javax.inject.Inject
import javax.inject.Singleton

class StackRepository {

    suspend fun getStackAnswers(page: String, pageSize: String, site: String):StackResponse {
       return ApiClient.API_SERVICE.getStackAnswers(page, pageSize, site)
    }

}