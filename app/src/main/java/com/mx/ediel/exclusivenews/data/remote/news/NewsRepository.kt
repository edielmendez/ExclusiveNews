package com.mx.ediel.exclusivenews.data.remote.news

import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.ui.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchNews(query: String): Flow<NetworkResult<List<News>>>
}