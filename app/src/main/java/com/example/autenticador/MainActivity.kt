package com.example.autenticador

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.autenticador.ui.theme.AutenticadorTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val validEmail = "email@email.com"
    private val validPassword = "email123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AutenticadorTheme {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }
        var message by remember { mutableStateOf("") }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Button(
                        onClick = {
                            validateCredentials(email.text, password.text) { success ->
                                message = if (success) {
                                    "Sucesso na Validação"
                                } else {
                                    "Falha na validação"
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Validar")
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(message)
                }
            }
        )
    }

    private fun validateCredentials(email: String, password: String, callback: (Boolean) -> Unit) {
        val isValid = (email == validEmail && password == validPassword)
        callback(isValid)
        if (isValid) {
            Log.i(TAG, "Login bem-sucedido")
        } else {
            Log.e(TAG, "Falha no login: Credenciais inválidas")
        }
    }
}
