package com.example.motorworld.navigation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motorworld.model.Circuito
import com.example.motorworld.repository.CircuitoRepository
import com.example.motorworld.screens.CircuitoDialog
import com.example.motorworld.screens.DetallesScreen
import com.example.motorworld.screens.ListaScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val circuitos = CircuitoRepository.getCircuitos()
    var mostrarDialogCrear by remember {
        mutableStateOf(false)
    }
    var mostrarDialogEditar by remember {
        mutableStateOf(false)
    }
    var circuitoEditar by remember {
        mutableStateOf<Circuito?>(null)
    }
    var mostrarDialogEliminar by remember {
        mutableStateOf(false)
    }
    var circuitoEliminar by remember {
        mutableStateOf<Circuito?>(null)
    }
    if (mostrarDialogCrear) {

        CircuitoDialog(

            circuito = null,

            onDismiss = {
                mostrarDialogCrear = false
            },

            onGuardar = { nuevoCircuito ->

                CircuitoRepository.addCircuito(
                    nuevoCircuito
                )

                mostrarDialogCrear = false

            }

        )

    }
    if (
        mostrarDialogEditar &&
        circuitoEditar != null
    ) {

        CircuitoDialog(

            circuito = circuitoEditar,

            onDismiss = {

                mostrarDialogEditar = false

            },

            onGuardar = { circuitoActualizado ->

                CircuitoRepository
                    .updateCircuito(
                        circuitoActualizado
                    )

                mostrarDialogEditar = false

            }

        )

    }
    if (
        mostrarDialogEliminar &&
        circuitoEliminar != null
    ) {

        AlertDialog(

            onDismissRequest = {
                mostrarDialogEliminar = false
            },

            title = {
                Text("Eliminar circuito")
            },

            text = {

                Text(
                    "¿Deseas eliminar ${circuitoEliminar!!.nombre}?"
                )

            },

            confirmButton = {

                Button(

                    onClick = {

                        CircuitoRepository
                            .deleteCircuito(
                                circuitoEliminar!!
                            )

                        mostrarDialogEliminar = false

                        navController.popBackStack()

                    }

                ) {

                    Text("Eliminar")

                }

            },

            dismissButton = {

                Button(
                    onClick = {
                        mostrarDialogEliminar = false
                    }
                ) {
                    Text("Cancelar")
                }

            }

        )

    }

    NavHost(
        navController = navController,
        startDestination = Routes.Lista.route
    ) {

        composable(
            route = Routes.Lista.route
        ) {

            ListaScreen(
                circuitos = circuitos,
                onCircuitoClick = { circuito ->

                    navController.navigate(
                        "detalle/${circuito.id}"
                    )

                },
                onCrearCircuito = {

                    mostrarDialogCrear = true
                }
            )

        }

        composable(
            route = Routes.Detalle.route
        ) { backStackEntry ->

            val id = backStackEntry
                .arguments
                ?.getString("id")
                ?.toLong()

            val circuito = circuitos.firstOrNull {
                it.id == id
            }
            if (circuito != null) {
                DetallesScreen(
                    circuito = circuito,
                    onVolver = {
                        navController.popBackStack()

                    },
                    onEditar = {
                        circuitoEditar = circuito

                        mostrarDialogEditar = true
                    },
                    onEliminar = {
                        circuitoEliminar = circuito

                        mostrarDialogEliminar = true
                    }
                )
            }
        }
    }
}
