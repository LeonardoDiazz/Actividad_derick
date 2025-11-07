package mx.edu.utez.calculadoramvvm.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.edu.utez.calculadoramvvm.data.SettingsRepository
import mx.edu.utez.calculadoramvvm.ui.SettingsUiState

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SettingsRepository(application.applicationContext)

    // 🔹 Estado que la UI observará
    val uiState: StateFlow<SettingsUiState> = repository.settingsFlow
        .map { data ->
            SettingsUiState(
                isDarkMode = data.isDarkMode,
                brightness = data.brightness,
                volume = data.volume,
                difficulty = data.difficulty
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsUiState()
        )

    // 🔹 Funciones para guardar valores
    fun setDarkMode(value: Boolean) = viewModelScope.launch {
        repository.setDarkMode(value)
    }

    fun setBrightness(value: Float) = viewModelScope.launch {
        repository.setBrightness(value)
    }

    fun setVolume(value: Float) = viewModelScope.launch {
        repository.setVolume(value)
    }

    fun setDifficulty(value: String) = viewModelScope.launch {
        repository.setDifficulty(value)
    }
}
