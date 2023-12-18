package com.mx.ediel.exclusivenews.data.remote.service

import com.mx.ediel.exclusivenews.data.remote.common.RemoteServiceConstants
import com.mx.ediel.exclusivenews.data.remote.news.respose.EverythingNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("q") query: String = "flutter",
        @Query("language") language: String = "es",
        @Query("apiKey") ApiKey: String = RemoteServiceConstants.API_KEY
    ): Response<EverythingNewsResponse>
}