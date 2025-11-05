package mx.edu.utez.calculadoramvvm.ui.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CajitaDecolorScreen(navController: NavController){
    var isBlueState by remember { mutableStateOf(false) }
    val boxColor = if (isBlueState) Color.Blue else Color.Green

    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Aqui esta la caja: ${if (isBlueState)  " Azul" else "verde"}")
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.size(100.dp).background(boxColor)
        )
        Spacer(modifier = Modifier.size(40.dp).background(boxColor))
        Button(onClick = {
            val nuevoEstado = !isBlueState
        }) {
            Text("cambiar color")
        }
    }
}