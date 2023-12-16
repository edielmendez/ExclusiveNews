package com.mx.ediel.exclusivenews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.screens.home.components.CustomSearchView
import com.mx.ediel.exclusivenews.ui.model.FakeNews
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFavoritesButtonClick: () -> Unit,
    onNewItemClick: (String) -> Unit
){
    //val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = "Exclusive News",
                rightIcon = Icons.Default.Home,
                onRightIconClick = onFavoritesButtonClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            CustomSearchView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
            NewsUiList(
                news = FakeNews.news,
                onItemClick = { news ->
                    onNewItemClick(news.id)
                }
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