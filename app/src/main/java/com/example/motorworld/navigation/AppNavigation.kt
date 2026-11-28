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
import com.example.motorworld.repository.AuthRepository
import com.example.motorworld.repository.CircuitoRepository
import com.example.motorworld.screens.BienvenidaScreen
import com.example.motorworld.screens.CircuitoDialog
import com.example.motorworld.screens.DetallesScreen
import com.example.motorworld.screens.HomeScreen
import com.example.motorworld.screens.ListaScreen
import com.example.motorworld.screens.LoginScreen
import com.example.motorworld.screens.RecuperarPasswordScreen
import com.example.motorworld.screens.RegistroScreen
import com.example.motorworld.screens.SplashScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val authRepository = remember { AuthRepository() }

    val destinoInicial = remember {
        if (authRepository.obtenerUsuarioActual() != null) {
            Routes.Home.route
        } else {
            Routes.Splash.route
        }
    }

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
        startDestination = destinoInicial
    ) {

        composable(route = Routes.Splash.route) {
            SplashScreen(
                onEntrarClick = {
                    navController.navigate(Routes.Bienvenida.route)
                }
            )
        }
        composable(route = Routes.Bienvenida.route) {
            BienvenidaScreen(
                onIniciarSesionClick = {
                    navController.navigate(Routes.Login.route)
                },
                onRegistroClick = {
                    navController.navigate(Routes.Registro.route)
                },
                onInvitadoClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Routes.Login.route) {
            LoginScreen(
                authRepository = authRepository,
                onLoginExito = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onIrARegistro = {
                    navController.navigate(Routes.Registro.route)
                },
                onIrARecuperarPassword = {
                    navController.navigate(Routes.RecuperarPassword.route)
                }
            )
        }
        composable(route = Routes.Registro.route) {
            RegistroScreen(
                authRepository = authRepository,
                onRegistroExito = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onVolverALogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Bienvenida.route)
                    }
                }
            )
        }
        composable(route = Routes.RecuperarPassword.route) {
            RecuperarPasswordScreen(
                authRepository = authRepository,
                onVolverALogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Routes.Home.route) {
            HomeScreen(
                emailUsuario = authRepository.obtenerUsuarioActual()?.email,
                onVerCircuitosClick = {
                    navController.navigate(Routes.Lista.route)
                },
                onCerrarSesionClick = {
                    authRepository.cerrarSesion()
                    navController.navigate(Routes.Bienvenida.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Routes.Lista.route) {
            ListaScreen(
                circuitos = circuitos,
                onCircuitoClick = { circuito ->
                    navController.navigate("detalle/${circuito.id}")
                },
                onCrearCircuito = {
                    mostrarDialogCrear = true
                }
            )
        }

        composable(route = Routes.Detalle.route) { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("id")
                ?.toLong()

            val circuito = circuitos.firstOrNull { it.id == id }
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
