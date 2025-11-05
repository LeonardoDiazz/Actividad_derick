package mx.edu.utez.calculadoramvvm.data.repository

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "user_data", //<---- nombre de la variable del SharedPreferences
        Context.MODE_PRIVATE //<---- Modo de acceso a esa variable
    )
    private val KEY_USER_NAME = "name_manual" //<---- nombre de la variable del SharedPreferences

    // Lectura: Lee directamente del disco
    fun getUserName(): String {
        return prefs.getString(KEY_USER_NAME, "Nombre NO GUARDADO") ?: "Nombre NO GUARDADO"
    }

    // Escritura: Guarda directamente en el disco
    fun saveUserName(name: String) {
        prefs.edit().putString(KEY_USER_NAME, name).apply()
    }
}