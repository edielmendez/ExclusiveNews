package com.mx.ediel.exclusivenews.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.EmptyResults
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.screens.home.HomeViewModel
import com.mx.ediel.exclusivenews.ui.screens.home.components.CustomSearchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onLeftIconClick: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = "Favoritos",
                leftIcon = Icons.Default.ArrowBack,
                onLeftIconClick = onLeftIconClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            if(uiState.newsList.isNotEmpty()){
                NewsUiList(
                    news = uiState.newsList,
                    onItemClick = {}
                )
            }else{
                EmptyResults()
            }
        }
    }
}