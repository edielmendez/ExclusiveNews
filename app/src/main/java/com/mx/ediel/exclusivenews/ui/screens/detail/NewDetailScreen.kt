package com.mx.ediel.exclusivenews.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mx.ediel.exclusivenews.ui.common.components.DefaultTopAppBar
import com.mx.ediel.exclusivenews.ui.common.components.NewsUiList
import com.mx.ediel.exclusivenews.ui.model.News
import com.mx.ediel.exclusivenews.ui.theme.BasePadding
import com.mx.ediel.exclusivenews.ui.theme.SmallPadding

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun NewDetailScreen(
    title: String,
    author: String,
    description: String,
    createdAt : String,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = author,
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
                modifier = Modifier.fillMaxWidth().height(250.dp),
                model = "https://cdn1.img.sputniknews.lat/images/sharing/article/spa/1146439268.jpg?10777304421702159381",
                contentDescription = title,
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = createdAt,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(BasePadding))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}