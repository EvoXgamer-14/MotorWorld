package com.example.motorworld.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.motorworld.model.Circuito

object CircuitoRepository{
    private val circuitos = mutableStateListOf(
        Circuito(
            id = 1,
            nombre = "Circuito de Jerez",
            ciudad = "Jerez",
            pais = "España",
            distancia = 4.42,
            descripcion = "El Circuito de Jerez-Ángel Nieto es uno de los autódromos " +
                    "más emblemáticos de España, " +
                    "inaugurado en 1985 y situado en Jerez de la Frontera (Cádiz, Andalucía). " +
                    "Con una longitud de 4.423 metros, " +
                    "es mundialmente famoso por albergar de forma ininterrumpida " +
                    "el Gran Premio de España de MotoGP y por ser el " +
                    "centro neurálgico de entrenamientos de pretemporada " +
                    "debido a su excelente clima invernal",
            logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2esYram2RJn7SNaVjqOmWckl48_EGpvQbxIHLuyWNf_lvvdzLf3Twcas&s=10"
        ),
        Circuito(
            id = 2,
            nombre = "Nordschleife",
            ciudad = "Nürburg",
            pais = "Alemania",
            distancia = 20.83,
            descripcion = "El Nordschleife, también conocido como " +
                    "el \"Infierno Verde\", " +
                    "es el legendario trazado norte del complejo de Nürburgring " +
                    "en Alemania. Inaugurado en 1927, " +
                    "este icónico circuito destaca por sus más de 20 kilómetros " +
                    "de longitud, alrededor de 70 curvas y constantes desniveles " +
                    "ciegos que desafían la habilidad de cualquier conductor. " +
                    "Actualmente, es famoso no solo por albergar históricas " +
                    "carreras de resistencia y pruebas de rendimiento de " +
                    "la industria automotriz, sino también por abrir " +
                    "sus puertas de forma regular como peaje abierto " +
                    "al público (Touristenfahrten), " +
                    "permitiendo que cualquier persona ruede en él " +
                    "con su vehículo privado.",
            logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQagMj3evc_QlMtUoQA71jZv8fA1e_Jeu8v3uICG1cdvg&s=10"
        ),
        Circuito(
            id = 3,
            nombre = "Mount Panorama Circuit",
            ciudad = "Bathurst",
            pais = "Australia",
            distancia = 6.21,
            descripcion = "El Circuito de Mount Panorama, también conocido internacionalmente" +
                    " como Bathurst, es una de las pistas de carreras más icónicas, " +
                    "desafiantes y peligrosas del mundo. " +
                    "Ubicado en Nueva Gales del Sur, Australia, " +
                    "este trazado semiurbano funciona como carretera " +
                    "pública en los días en que no hay eventos programados. " +
                    "Su combinación única de largas rectas y " +
                    "una sección montañosa extremadamente estrecha flanqueada " +
                    "por muros de hormigón lo convierten en un verdadero " +
                    "templo del automovilismo mundial",
            logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrrVhOVdtiIKSggdJXH9xBYUV5fSdDG_OXZl0Jfpcrag&s=10"
        ),
        Circuito(
            id = 4,
            nombre = "Spa-Francorchamps",
            ciudad = "Stavelot",
            pais = "Belgica",
            distancia = 7.004,
            descripcion = "El Circuito de Spa-Francorchamps, ubicado en el bosque de las Ardenas en Bélgica," +
                    " es uno de los trazados más históricos, rápidos y exigentes del automovilismo mundial." +
                    " Inaugurado en 1921 y remodelado en 1979, destaca por sus 7,004 kilómetros de longitud," +
                    " sus fuertes desniveles naturales de casi 100 metros y su clima impredecible.",
            logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdGDdPvd46ENrN8W9-hzMo_ccDZJ_t1ycq9FcRoVXliQ&s=10"
        )
    )


    fun getCircuitos(): MutableList<Circuito>{
        return circuitos
    }
    fun addCircuito(
        circuito: Circuito
    ) {
        val nuevoId = (circuitos.maxOfOrNull { it.id } ?: 0L) + 1
        circuitos.add(circuito.copy(id = nuevoId))
    }
    fun deleteCircuito(
        circuito: Circuito
    ) {
        circuitos.remove(circuito)
    }
    fun updateCircuito(
        circuitoActualizado: Circuito
    ) {

        val index =
            circuitos.indexOfFirst {
                it.id == circuitoActualizado.id
            }

        if(index != -1) {

            circuitos[index] =
                circuitoActualizado

        }

    }
}