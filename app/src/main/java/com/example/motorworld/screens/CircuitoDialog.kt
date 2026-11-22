package com.example.motorworld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.motorworld.model.Circuito

@Composable
fun CircuitoDialog(
    circuito: Circuito?,
    onDismiss: () -> Unit,
    onGuardar: (Circuito) -> Unit
) {

    var nombre by remember { mutableStateOf(circuito?.nombre ?: "") }
    var ciudad by remember { mutableStateOf(circuito?.ciudad ?: "") }
    var pais by remember { mutableStateOf(circuito?.pais ?: "") }
    var distancia by remember { mutableStateOf(circuito?.distancia?.toString() ?: "") }
    var logoUrl by remember { mutableStateOf(circuito?.logoUrl ?: "") }
    var descripcion by remember { mutableStateOf(circuito?.descripcion ?: "") }


    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorCiudad by remember { mutableStateOf<String?>(null) }
    var errorPais by remember { mutableStateOf<String?>(null) }
    var errorDistancia by remember { mutableStateOf<String?>(null) }
    var errorLogo by remember { mutableStateOf<String?>(null) }
    var errorDescripcion by remember { mutableStateOf<String?>(null) }


    fun validar(): Boolean {
        errorNombre = if (nombre.isBlank()) "El nombre es obligatorio" else null
        errorCiudad = if (ciudad.isBlank()) "La ciudad es obligatoria" else null
        errorPais = if (pais.isBlank()) "El país es obligatorio" else null

        val valor = distancia.trim().replace(',', '.').toDoubleOrNull()
        errorDistancia = when {
            distancia.isBlank() -> "La distancia es obligatoria"
            valor == null -> "Introduce un número válido (ej. 4.42)"
            valor <= 0.0 -> "La distancia debe ser mayor que 0"
            else -> null
        }

        errorLogo = if (logoUrl.isNotBlank() && !logoUrl.trim().startsWith("http"))
            "La URL debe empezar por http o https"
        else null

        errorDescripcion = if (descripcion.isBlank()) "La descripción es obligatoria" else null

        return listOf(
            errorNombre, errorCiudad, errorPais,
            errorDistancia, errorLogo, errorDescripcion
        ).all { it == null }
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(if (circuito == null) "Crear circuito" else "Editar circuito")
        },

        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it; errorNombre = null },
                    label = { Text("Nombre") },
                    isError = errorNombre != null,
                    supportingText = errorNombre?.let { msg -> { Text(msg) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = ciudad,
                    onValueChange = { ciudad = it; errorCiudad = null },
                    label = { Text("Ciudad") },
                    isError = errorCiudad != null,
                    supportingText = errorCiudad?.let { msg -> { Text(msg) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = pais,
                    onValueChange = { pais = it; errorPais = null },
                    label = { Text("País") },
                    isError = errorPais != null,
                    supportingText = errorPais?.let { msg -> { Text(msg) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = logoUrl,
                    onValueChange = { logoUrl = it; errorLogo = null },
                    label = { Text("URL Logo (opcional)") },
                    isError = errorLogo != null,
                    supportingText = errorLogo?.let { msg -> { Text(msg) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = distancia,
                    onValueChange = { distancia = it; errorDistancia = null },
                    label = { Text("Distancia (km)") },
                    isError = errorDistancia != null,
                    supportingText = errorDistancia?.let { msg -> { Text(msg) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it; errorDescripcion = null },
                    label = { Text("Descripción") },
                    isError = errorDescripcion != null,
                    supportingText = errorDescripcion?.let { msg -> { Text(msg) } },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {
            Button(
                onClick = {

                    if (validar()) {
                        onGuardar(
                            Circuito(
                                id = circuito?.id ?: 0L,
                                nombre = nombre.trim(),
                                ciudad = ciudad.trim(),
                                pais = pais.trim(),
                                distancia = distancia.trim().replace(',', '.').toDouble(),
                                descripcion = descripcion.trim(),
                                logoUrl = logoUrl.trim()
                            )
                        )
                    }
                }
            ) {
                Text("Guardar")
            }
        },

        dismissButton = {

            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
