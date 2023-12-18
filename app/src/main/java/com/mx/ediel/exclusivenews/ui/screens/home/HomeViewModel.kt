package com.mx.ediel.exclusivenews.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.ediel.exclusivenews.data.remote.common.NetworkResult
import com.mx.ediel.exclusivenews.data.remote.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
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

    init {
        resetPagination()
    }

    private fun resetPagination(){
        vmUiState.update {
            it.copy(page = 1)
        }
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.FetchNews -> {
                fetchNews()
            }
            is HomeEvent.Search -> {
                searchWord(event.word)
            }
            is HomeEvent.ResetList -> {
                resetNewsList()
            }
        }
    }

    private fun fetchNews() {
        job?.cancel()
        setLoading()
        job = viewModelScope.launch(context = Dispatchers.IO) {
            repository.fetchNews("flutter")
                .catch {  }
                .collectLatest { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            vmUiState.update { state ->
                                val newList = state.newsList.toMutableList()
                                newList.addAll(result.data)
                                state.copy(isLoading = false, newsList = newList,  newsListBackup = newList)
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

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchWord(word: String){
        /*val filteredList = uiState.value.newsList.filter { movie ->
            movie.title.contains(word, true) || movie.description.contains(word, true) || movie.author.contains(word, true)
        }
        vmUiState.update {
            it.copy(newsList = filteredList)
        }*/
        job?.cancel()
        job = viewModelScope.launch {
            flowOf(word)
                .debounce(500)
                .filter { query ->
                    return@filter query.length > 3
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    Log.v("HomeViewModel", "Searching ... $it")
                    repository.fetchNews(it)
                }
                .catch {  }
                .collectLatest { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            vmUiState.update { state ->
                                state.copy(isLoading = false, newsList = result.data)
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

    private fun resetNewsList(){
        job?.cancel()
        vmUiState.update {
            it.copy(newsList = it.newsListBackup)
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