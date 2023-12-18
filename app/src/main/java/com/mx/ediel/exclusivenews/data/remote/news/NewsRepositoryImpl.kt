package com.mx.ediel.exclusivenews.data.remote.news

import com.mx.ediel.exclusivenews.data.local.dao.NewsDAO
import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.data.remote.service.AppService
import com.mx.ediel.exclusivenews.di.DefaultDispatcher
import com.mx.ediel.exclusivenews.ui.model.News
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor (
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: AppService,
    private val dao: NewsDAO
): NewsRepository {

    val localNews = dao.getNews().map { it.map { list -> list.toNewsModel() } }
    override fun fetchNews(query: String): Flow<NetworkResult<List<News>>> = flow {
        try {
            val response = service.getNews(
                query = query
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