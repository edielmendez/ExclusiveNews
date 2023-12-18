package com.mx.ediel.exclusivenews.data.local.repositories

import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.ui.model.News
import kotlinx.coroutines.flow.Flow

interface NewsLocalRepository {
    fun fetchNews(): Flow<List<News>>
}