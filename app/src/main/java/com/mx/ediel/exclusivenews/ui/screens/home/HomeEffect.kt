package com.mx.ediel.exclusivenews.ui.screens.home

import com.mx.ediel.exclusivenews.ui.common.viewmodel.UiEffect

sealed interface HomeEffect: UiEffect {
    data class ShowToast(val message: String) : HomeEffect
}