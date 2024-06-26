package com.mx.ediel.exclusivenews.ui.screens.home

import com.mx.ediel.exclusivenews.ui.common.viewmodel.UiEvent

sealed interface HomeEvent: UiEvent {
    object FetchNews: HomeEvent
    data class Search(val word: String): HomeEvent
    object ResetList: HomeEvent
}