package com.mx.ediel.exclusivenews.ui.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = {}
    )
}