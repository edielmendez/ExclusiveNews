package com.mx.ediel.exclusivenews.ui.screens.signin

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mx.ediel.exclusivenews.R
import com.mx.ediel.exclusivenews.ui.common.utils.emailValid
import com.mx.ediel.exclusivenews.ui.common.utils.passwordValid
import com.mx.ediel.exclusivenews.ui.theme.BasePadding
import com.mx.ediel.exclusivenews.ui.theme.ExclusiveNewsTheme
import com.mx.ediel.exclusivenews.ui.theme.LargePadding
import com.mx.ediel.exclusivenews.ui.theme.MediumPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignInButtonClick: () -> Unit
){
    var emailValid by remember {
        mutableStateOf(false)
    }
    var passwordValid by remember {
        mutableStateOf(false)
    }
    val buttonEnabled by remember {
        derivedStateOf { emailValid && passwordValid }
    }
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
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
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            text = "Hi there! Nice to see you again"
        )
        Spacer(modifier = Modifier.height(MediumPadding))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailText,
            onValueChange = {
                emailText = it
                emailValid = it.emailValid()
            },
            label = { Text(text = "Email") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrect = false,
                imeAction = ImeAction.Next
            )
        )
        if(!emailValid && emailText.isNotEmpty()){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Correo no válido, intenta de nuevo",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordText,
            onValueChange = {
                passwordText = it
                passwordValid = it.passwordValid()
            },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches() && passwordValid) {
                    focusManager.clearFocus(true)
                }
            }),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )
        if(!passwordValid && passwordText.isNotEmpty()){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Contraseña no válida, intenta de nuevo",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(LargePadding))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignInButtonClick,
            enabled = buttonEnabled
        ) {
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