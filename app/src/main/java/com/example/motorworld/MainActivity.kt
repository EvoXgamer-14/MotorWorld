package com.example.motorworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.motorworld.navigation.AppNavigation

import com.example.motorworld.ui.theme.MotorWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotorWorldTheme {
                AppNavigation()
            }
        }
    }
}
