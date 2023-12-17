package com.mx.ediel.exclusivenews.ui.screens.home

import com.mx.ediel.exclusivenews.ui.model.News

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val newsList: List<News> = emptyList(),
    val page: Int = 1
)
