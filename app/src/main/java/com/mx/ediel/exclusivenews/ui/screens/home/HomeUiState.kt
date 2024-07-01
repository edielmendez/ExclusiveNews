package com.mx.ediel.exclusivenews.ui.screens.home

import com.mx.ediel.exclusivenews.ui.common.viewmodel.UiState
import com.mx.ediel.exclusivenews.ui.model.News
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val newsList: List<News> = emptyList(),
    val newsListBackup: List<News> = emptyList(),
    val isRefreshing: Boolean = false
    //val page: Int = 1
): UiState


sealed interface DataState<out T> {
    object Loading: DataState<Nothing>
    data class Success<T>(val data: T): DataState<T>
    data class Error(val message: String): DataState<Nothing>

    companion object {
        @OptIn(ExperimentalContracts::class)
        fun <T> DataState<T>.isSuccess(): Boolean {
            contract {
                returns(true) implies (this@isSuccess is Success)
            }
            return this is Success
        }

        fun <T> DataState<T>.asSuccess() = this as Success

        fun <T> DataState<T>.asError() = this as Error
    }
}