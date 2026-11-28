package com.example.motorworld.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun obtenerUsuarioActual(): FirebaseUser? {
        return auth.currentUser
    }

    fun iniciarSesion(
        email: String,
        password: String,
        onExito: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener {
                onExito()
            }
            .addOnFailureListener { excepcion ->
                onError(traducirErrorFirebase(excepcion))
            }
    }

    fun registrarUsuario(
        email: String,
        password: String,
        onExito: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener {
                onExito()
            }
            .addOnFailureListener { excepcion ->
                onError(traducirErrorFirebase(excepcion))
            }
    }

    fun recuperarPassword(
        email: String,
        onExito: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email.trim())
            .addOnSuccessListener {
                onExito()
            }
            .addOnFailureListener { excepcion ->
                onError(traducirErrorFirebase(excepcion))
            }
    }


    fun cerrarSesion() {
        auth.signOut()
    }

    private fun traducirErrorFirebase(excepcion: Exception): String {
        val codigo = (excepcion as? FirebaseAuthException)?.errorCode
        return when (codigo) {
            "ERROR_INVALID_EMAIL" -> "El formato del correo electrónico no es válido."
            "ERROR_WRONG_PASSWORD", "ERROR_INVALID_CREDENTIAL" -> "Correo o contraseña incorrectos."
            "ERROR_USER_NOT_FOUND" -> "No existe ninguna cuenta con ese correo."
            "ERROR_USER_DISABLED" -> "Esta cuenta ha sido deshabilitada."
            "ERROR_EMAIL_ALREADY_IN_USE" -> "Ya existe una cuenta registrada con este correo."
            "ERROR_WEAK_PASSWORD" -> "La contraseña es muy débil (mínimo 6 caracteres)."
            "ERROR_TOO_MANY_REQUESTS" -> "Demasiados intentos fallidos. Inténtalo más tarde."
            "ERROR_NETWORK_REQUEST_FAILED" -> "Sin conexión a internet. Revisa tu red."
            else -> excepcion.localizedMessage ?: "Ha ocurrido un error inesperado."
        }
    }
}