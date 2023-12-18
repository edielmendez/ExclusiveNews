package com.mx.ediel.exclusivenews.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mx.ediel.exclusivenews.ui.model.News
import com.mx.ediel.exclusivenews.ui.theme.BasePadding
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme
import com.mx.ediel.exclusivenews.ui.theme.SmallPadding
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NewsUiList(
    modifier: Modifier = Modifier,
    news: List<News>,
    onItemClick: (News) -> Unit,
    onBottomReached: () -> Unit = {}
){
    val listState = rememberLazyListState()
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 16.dp
        ),
        state = listState
    ){
        items(news){
            NewItemRow(
                onItemClick = onItemClick,
                item = it
            )
        }
    }
    listState.OnBottomReached(loadMore = onBottomReached)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewItemRow(
    modifier: Modifier = Modifier,
    item: News,
    onItemClick: (News) -> Unit
){
    Spacer(modifier = Modifier.height(24.dp))
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable {
                    onItemClick(item)
                }
        ){
            Column(
                modifier = Modifier
                    .weight(0.6F)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.author,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(SmallPadding))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = item.description.take(80),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(SmallPadding))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.createdAt,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            GlideImage(
                modifier = Modifier
                    .weight(0.4F)
                    .fillMaxHeight(),
                model = item.image,
                contentDescription = item.title,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
private fun LazyListState.OnBottomReached(
    loadMore : () -> Unit,
    buffer: Int = 2
){
    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = this.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItems - buffer)
        }
    }

    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect {
                if(it)
                    loadMore()
            }
    }
}

@Preview
@Composable
fun NewItemRowPreview(){
    ExclusiveNewsTheme {
        NewItemRow(
            onItemClick = {},
            item = News(
                id = "",
                author = "Richard Lai",
                title = "Apple now needs a judge's order to hand over push notification records",
                createdAt = "2023-12-14T12:55:48Z",
                image = "https://ichef.bbci.co.uk/news/1024/branded_news/8665/production/_132050443_gettyimages-1277207512-1.jpg",
                description = "Tesco has recalled a batch of Christmas stuffing mix due to the possible presence of moths in the product.\\r\\nThe batch of Tesco Finest Apple &amp; Cranberry Stuffing Mix may be \\\"unfit for human consum… [+538 chars]"
            )
        )
    }
}