package com.mx.ediel.exclusivenews.data.local.repositories

import com.mx.ediel.exclusivenews.data.local.dao.NewsDAO
import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.ui.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalRepositoryImpl @Inject constructor(
    private val dao: NewsDAO
):NewsLocalRepository {
    override fun fetchNews() = dao.getNews().map { newsList -> newsList.map { it.toNewsModel() } }
}