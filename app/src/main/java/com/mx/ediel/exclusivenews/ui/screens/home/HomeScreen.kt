package com.mx.ediel.exclusivenews.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mx.ediel.exclusivenews.ui.common.components.CustomLoader
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.EmptyResults
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.model.News
import com.mx.ediel.exclusivenews.ui.screens.home.components.CustomSearchView
import com.mx.ediel.exclusivenews.ui.screens.home.components.DPHomeShimmerScreen
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onFavoritesButtonClick: () -> Unit,
    onNewItemClick: (News) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
){
    //val uiState by viewModel.uiState.collectAsState()
    val uiState  = viewModel.state
    var searchedText by remember {
        mutableStateOf("")
    }
    val pullRefreshState = rememberPullRefreshState(uiState.isRefreshing, { viewModel.setEvent(HomeEvent.FetchNews) })

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when(it){
                is HomeEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /*LaunchedEffect(Unit){
        viewModel.handleEvent(HomeEvent.FetchNews)
    }*/
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = "Exclusive News",
                rightIcon = Icons.Filled.Favorite,
                onRightIconClick = onFavoritesButtonClick
            )
        }
    ) {
        Box(
            Modifier.padding(it)
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {

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
                            viewModel.setEvent(HomeEvent.Search(it))
                        }else{
                            viewModel.setEvent(HomeEvent.ResetList)
                        }
                    },
                    placeHolder = "Buscar",

                )
                if(viewModel.state.isLoading){
                    //CustomLoader()
                    DPHomeShimmerScreen()
                }
                if(uiState.newsList.isNotEmpty()){
                    NewsUiList(
                        news = uiState.newsList,
                        onItemClick = { news ->
                            onNewItemClick(news)
                        }
                    )
                }else{
                    EmptyResults(
                        message = "RESULTADOS NO ENCONTRADOS"
                    )
                }
            }

            PullRefreshIndicator(
                refreshing = uiState.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
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