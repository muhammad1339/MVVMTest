package com.proprog.applicationtest.data.api;

import com.proprog.applicationtest.data.model.StackResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("answers")
    suspend fun getStackAnswers(
        @Query("page") page: String,
        @Query("pagesize") pageSize: String,
        @Query("site") site: String,
    ): StackResponse

    // Get("articles?site=stackoverflow")
}
