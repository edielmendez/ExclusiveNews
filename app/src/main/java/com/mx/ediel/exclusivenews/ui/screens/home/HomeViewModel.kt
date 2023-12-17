package com.mx.ediel.exclusivenews.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.data.remote.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {
    private val vmUiState =
        MutableStateFlow(HomeUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    private var job: Job? = null

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.FetchNews -> {
                fetchNews()
            }
            is HomeEvent.Search -> {

            }
        }
    }

    private fun fetchNews() {
        job?.cancel()
        job = viewModelScope.launch(context = Dispatchers.IO) {
            repository.fetchNews(uiState.value.page)
                .catch {  }
                .collectLatest { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            vmUiState.update { state ->
                                val newList = state.newsList.toMutableList()
                                newList.addAll(result.data)
                                state.copy(isLoading = false, newsList = newList)
                            }
                        }
                        is NetworkResult.Error -> {
                            vmUiState.update { state ->
                                state.copy(isLoading = false, error = result.error)
                            }
                        }
                    }
                }
        }

    }
}