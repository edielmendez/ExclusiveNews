package com.mx.ediel.exclusivenews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mx.ediel.exclusivenews.ui.common.components.CustomLoader
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.screens.home.components.CustomSearchView
import com.mx.ediel.exclusivenews.ui.model.FakeNews
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFavoritesButtonClick: () -> Unit,
    onNewItemClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    var searchedText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit){
        viewModel.onEvent(HomeEvent.FetchNews)
    }
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = "Exclusive News",
                rightIcon = Icons.Filled.Favorite,
                onRightIconClick = onFavoritesButtonClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if(uiState.isLoading){
                CustomLoader()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                CustomSearchView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, top = 16.dp),
                    searchedText = searchedText,
                    onValueChange = {
                        searchedText = it
                        if(it.isNotEmpty()){
                            viewModel.onEvent(HomeEvent.Search(it))
                        }else{
                            viewModel.onEvent(HomeEvent.ResetList)
                        }
                    },
                    placeHolder = "Buscar",

                )
                NewsUiList(
                    news = uiState.newsList,
                    onItemClick = { news ->
                        onNewItemClick(news.id)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    ExclusiveNewsTheme {
        HomeScreen(
            onFavoritesButtonClick = {},
            onNewItemClick = {}
        )
    }
}