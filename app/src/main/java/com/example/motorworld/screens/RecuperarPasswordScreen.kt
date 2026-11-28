package com.example.motorworld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.motorworld.repository.AuthRepository

@Composable
fun RecuperarPasswordScreen(
    authRepository: AuthRepository,
    onVolverALogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var correoEnviado by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recuperar contraseña",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Introduce tu correo y te enviaremos un enlace para restablecerla.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    mensajeError = null
                    correoEnviado = false
                },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            if (mensajeError != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = mensajeError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (correoEnviado) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Correo enviado. Revisa tu bandeja de entrada (y spam).",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.isBlank()) {
                        mensajeError = "Introduce tu correo electrónico."
                        return@Button
                    }
                    cargando = true
                    mensajeError = null
                    authRepository.recuperarPassword(
                        email = email,
                        onExito = {
                            cargando = false
                            correoEnviado = true
                        },
                        onError = { error ->
                            cargando = false
                            mensajeError = error
                        }
                    )
                },
                enabled = !cargando,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (cargando) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Enviar correo de recuperación")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onVolverALogin) {
                Text("Volver al inicio de sesión")
            }
        }
    }
}