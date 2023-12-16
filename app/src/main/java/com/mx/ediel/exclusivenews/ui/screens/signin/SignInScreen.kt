package com.mx.ediel.exclusivenews.ui.screens.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mx.ediel.exclusivenews.R
import com.mx.ediel.exclusivenews.ui.theme.BasePadding
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignInButtonClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp),
            text = stringResource(id = R.string.app_name)
        )
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Sign In",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            text = "Hi there! Nice to see you again"
        )
        Spacer(modifier = Modifier.height(BasePadding))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "", onValueChange = {}
        )
        Spacer(modifier = Modifier.height(BasePadding))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "", onValueChange = {}
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignInButtonClick) {
            Text(text = "Sign In")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignInScreenPreview(){
    ExclusiveNewsTheme{
        SignInScreen({})
    }
}