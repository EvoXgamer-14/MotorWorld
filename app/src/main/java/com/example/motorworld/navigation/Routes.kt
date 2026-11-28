package com.example.motorworld.navigation

sealed class Routes(
    val route: String
) {
    object Splash : Routes("splash")

    object Bienvenida : Routes("bienvenida")

    object Login : Routes("login")

    object Registro : Routes("registro")

    object RecuperarPassword : Routes("recuperar_password")

    object Home : Routes("home")
    object Lista : Routes("lista")
    object Detalle : Routes("detalle/{id}")

}