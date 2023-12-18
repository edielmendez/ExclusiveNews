package com.mx.ediel.exclusivenews.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoader(){
    Surface(
        shadowElevation = 8.dp
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
            .height(8.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}