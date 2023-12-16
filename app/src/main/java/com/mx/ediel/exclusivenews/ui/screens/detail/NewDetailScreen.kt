package com.mx.ediel.exclusivenews.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.model.FakeNews

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun NewDetailScreen(
    newsId: String,
    newsTitle: String,
    newsAuthor: String,
    newsImage: String,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = newsId,
                leftIcon = Icons.Default.ArrowBack,
                rightIcon = Icons.Default.Delete,
                onLeftIconClick = onBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it).padding(horizontal = 16.dp)
        ) {
            GlideImage(
                modifier = Modifier.fillMaxWidth(),
                model = newsImage,
                contentDescription = newsTitle,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}