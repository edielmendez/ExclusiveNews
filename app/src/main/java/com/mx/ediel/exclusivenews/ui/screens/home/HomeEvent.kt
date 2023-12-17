package com.mx.ediel.exclusivenews.ui.screens.home

sealed interface HomeEvent{
    object FetchNews: HomeEvent
    data class Search(val word: String): HomeEvent
}