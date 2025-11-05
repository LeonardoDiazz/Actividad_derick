package mx.edu.utez.calculadoramvvm.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.utez.calculadoramvvm.data.repository.PreferenceManager
import mx.edu.utez.gato.viewmodel.LoginViewModel

@Composable
fun NamePreferenceScreen(navController: NavController, viewModel: LoginViewModel) {
    val context = LocalContext.current.applicationContext
    val prefsManager = remember { PreferenceManager(context) }

    var nameState by remember { mutableStateOf("") }
    var isSaved by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val savedName = prefsManager.getUserName()
        if (savedName != "Nombre NO GUARDADO") {
            nameState = savedName
            isSaved = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nombre del usuario")
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nameState,
            onValueChange = {
                nameState = it
                isSaved = false
            },
            label = { Text("Ingresa tu nombre") },
            placeholder = { Text("Escribe aquí tu nombre") }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                prefsManager.saveUserName(nameState)
                isSaved = true
            }
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(10.dp))


        if (isSaved && nameState.isNotBlank()) {
            Text("Bienvenido de nuevo, $nameState ")
        } else {
            Text("No te topo")
        }
    }
}