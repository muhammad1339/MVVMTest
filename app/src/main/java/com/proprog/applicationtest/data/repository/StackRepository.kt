package com.proprog.applicationtest.data.repository

import com.proprog.applicationtest.data.api.ApiClient
import com.proprog.applicationtest.data.model.StackResponse

class StackRepository {

    suspend fun getStackAnswers(page: String, pageSize: String, site: String): StackResponse {
        return ApiClient.API_SERVICE.getStackAnswers(page, pageSize, site)
    }


}