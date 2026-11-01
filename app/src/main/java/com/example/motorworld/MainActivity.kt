package com.example.motorworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.motorworld.model.Circuito
import com.example.motorworld.screens.DetallesScreen
import com.example.motorworld.screens.ListaScreen
import com.example.motorworld.ui.theme.MotorWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotorWorldTheme {
               val ciurcuitos = listOf(
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
                       logoUrl = "Logo"
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
                       logoUrl = "Logo"
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
                    logoUrl = "Logo"
                )
               )
                var circuitoSeleccionado by remember{
                    mutableStateOf<Circuito?>(null)
                }
                if (circuitoSeleccionado == null) {
                    ListaScreen(
                        ciurcuitos,
                        onCircuitoClick = {
                            circuitoSeleccionado = it
                        }
                    )
                }else{
                    DetallesScreen(
                        circuito = circuitoSeleccionado!!,
                        onVolver = {
                            circuitoSeleccionado = null
                        }
                    )
               }
            }
        }
    }
}
