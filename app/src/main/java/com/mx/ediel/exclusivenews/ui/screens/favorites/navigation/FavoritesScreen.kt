package com.mx.ediel.exclusivenews.ui.screens.favorites.navigation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.model.FakeNews
import com.mx.ediel.exclusivenews.ui.screens.home.components.CustomSearchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onRightIconClick: () -> Unit
){
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = "Favoritos",
                leftIcon = Icons.Default.ArrowBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it).padding(horizontal = 16.dp)
        ) {
            NewsUiList(
                news = FakeNews.news,
                onItemClick = {}
            )
        }
    }
}