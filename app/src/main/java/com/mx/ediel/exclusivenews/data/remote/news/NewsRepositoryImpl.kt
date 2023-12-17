package com.mx.ediel.exclusivenews.data.remote.news

import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.data.remote.service.AppService
import com.mx.ediel.exclusivenews.di.DefaultDispatcher
import com.mx.ediel.exclusivenews.ui.model.News
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor (
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: AppService,
): NewsRepository {
    override fun fetchNews(page: Int): Flow<NetworkResult<List<News>>> = flow {
        try {
            val response = service.getNews(
                page = page
            )
            if(response.isSuccessful){
                emit(NetworkResult.Success(response.body()?.articles?.map { it.toNews() } ?: emptyList()))
            }else{
                emit(NetworkResult.Error(response.errorBody().toString()))
            }
        }catch (exception: Exception){
            emit(NetworkResult.Error(error = exception.message ?: ""))
        }
    }
}