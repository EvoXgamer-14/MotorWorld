package com.example.motorworld.model

data class Circuito(
    val id: Long,
    val nombre: String,
    val ciudad: String,
    val pais: String,
    val distancia: Double,
    val descripcion: String,
    val logoUrl: String
)