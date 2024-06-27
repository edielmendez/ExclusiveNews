package com.mx.ediel.exclusivenews.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.mx.ediel.exclusivenews.ui.common.compose.shimmerEffect
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme
import com.mx.ediel.exclusivenews.ui.theme.SmallPadding

@Composable
fun DPHomeShimmerItem(
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ){
        Column(
            modifier = Modifier
                .weight(0.6F)
                .fillMaxHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(16.dp).shimmerEffect()
            )
            //Spacer(modifier = Modifier.height(SmallPadding))
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(16.dp).shimmerEffect()
            )
            //Spacer(modifier = Modifier.height(SmallPadding))
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(16.dp).shimmerEffect()
            )
        }
        Box(
            modifier = Modifier
                .weight(0.4F)
                .padding(16.dp)
                .fillMaxHeight().shimmerEffect()
        )
    }
    /*Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Box(modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shimmerEffect()
        )
    }*/
}

@Composable
fun DPHomeShimmerScreen(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(5){
                DPHomeShimmerItem()
            }
        }
    }
}


@Preview
@Composable
fun DPHomeShimmerItemPrev(

){
    ExclusiveNewsTheme {
        Surface {
            DPHomeShimmerItem()
        }
    }
}