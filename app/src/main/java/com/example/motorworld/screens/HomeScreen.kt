package com.example.motorworld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    emailUsuario: String?,
    onVerCircuitosClick: () -> Unit,
    onCerrarSesionClick: () -> Unit
) {
    val nombreMostrar = emailUsuario?.substringBefore("@") ?: "Invitado"

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Cabecera con botón de cerrar sesión a la derecha (como en tu wireframe)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(onClick = onCerrarSesionClick) {
                    Text(if (emailUsuario != null) "Cerrar sesión" else "Salir de invitado")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hola de nuevo,",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "@$nombreMostrar",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onVerCircuitosClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Explorar Circuitos")
                }
            }
        }
    }
}