package com.example.motorworld.navigation

sealed class Routes(
    val route: String
) {

    object Lista : Routes("lista")

    object Detalle : Routes("detalle/{id}")

}