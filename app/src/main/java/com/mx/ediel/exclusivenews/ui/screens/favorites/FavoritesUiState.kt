package com.mx.ediel.exclusivenews.ui.screens.favorites

import com.mx.ediel.exclusivenews.ui.model.News

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val newsList: List<News> = emptyList()
)
