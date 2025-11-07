package mx.edu.utez.calculadoramvvm.ui

data class SettingsUiState(
    val isDarkMode: Boolean = false,
    val brightness: Float = 0.5f, // 0.0f a 1.0f (slider)
    val volume: Float = 0.5f,     // 0.0f a 1.0f (slider)
    val difficulty: String = "Normal"
)
