 package com.mx.ediel.exclusivenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mx.ediel.exclusivenews.ui.navigation.ExclusiveNewsNavHost
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExclusiveNewsTheme {
                ExclusiveNewsNavHost()
            }
        }
    }
}