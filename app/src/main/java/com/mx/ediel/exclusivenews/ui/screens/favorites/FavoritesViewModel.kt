package com.mx.ediel.exclusivenews.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.ediel.exclusivenews.data.local.repositories.NewsLocalRepository
import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.data.remote.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: NewsLocalRepository
): ViewModel() {
    private val vmUiState =
        MutableStateFlow(FavoritesUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        fetchNews()
    }

    private var job: Job? = null

    private fun fetchNews() {
        job?.cancel()
        setLoading()
        job = viewModelScope.launch(context = Dispatchers.IO) {
            repository.fetchNews()
                .catch { }
                .collectLatest { newList ->
                    vmUiState.update { state ->
                        state.copy(isLoading = false, newsList = newList)
                    }
                }
        }

    }

    private fun setLoading(isLoading: Boolean = true){
        vmUiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}