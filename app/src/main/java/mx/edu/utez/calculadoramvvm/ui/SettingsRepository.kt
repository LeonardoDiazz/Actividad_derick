package mx.edu.utez.calculadoramvvm.data
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 🔹 Crea la instancia DataStore
val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class SettingsRepository(private val context: Context) {

    // 🔹 Definimos las claves
    private object Keys {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val BRIGHTNESS = floatPreferencesKey("brightness")
        val VOLUME = floatPreferencesKey("volume")
        val DIFFICULTY = stringPreferencesKey("difficulty")
    }

    // 🔹 Leer los valores
    val settingsFlow: Flow<SettingsData> = context.dataStore.data.map { prefs ->
        SettingsData(
            isDarkMode = prefs[Keys.DARK_MODE] ?: false,
            brightness = prefs[Keys.BRIGHTNESS] ?: 0.5f,
            volume = prefs[Keys.VOLUME] ?: 0.5f,
            difficulty = prefs[Keys.DIFFICULTY] ?: "Normal"
        )
    }

    // 🔹 Guardar valores
    suspend fun setDarkMode(value: Boolean) {
        context.dataStore.edit { it[Keys.DARK_MODE] = value }
    }

    suspend fun setBrightness(value: Float) {
        context.dataStore.edit { it[Keys.BRIGHTNESS] = value }
    }

    suspend fun setVolume(value: Float) {
        context.dataStore.edit { it[Keys.VOLUME] = value }
    }

    suspend fun setDifficulty(value: String) {
        context.dataStore.edit { it[Keys.DIFFICULTY] = value }
    }
}

// 🔹 Modelo interno de datos
data class SettingsData(
    val isDarkMode: Boolean,
    val brightness: Float,
    val volume: Float,
    val difficulty: String
)
