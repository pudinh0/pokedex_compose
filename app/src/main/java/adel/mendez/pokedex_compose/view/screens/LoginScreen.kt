package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun LoginScreen(innerPaddingValues: PaddingValues, onNavigateToMenu: () -> Unit, viewModel: UserViewModel = viewModel()){
    var correo by rememberSaveable { mutableStateOf("") }
    var password = remember { TextFieldState() }


    LaunchedEffect(viewModel.currentUser) {
        if (viewModel.currentUser != null) onNavigateToMenu()
    }
    Column(modifier = Modifier
        .padding(innerPaddingValues)
        .padding(15.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        TextField(
            value = correo,
            onValueChange = {correo = it},
            label = {Text("Correo electronico:",
                fontSize = 16.sp,
                color = Color.DarkGray)},
            singleLine = true)

        Spacer(modifier = Modifier.size(15.dp))
        SecureTextField(
            state = password,
            label = {Text("Contraseña:")
                    },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.size(15.dp))
        Button(onClick = { viewModel.login(correo, password.text.toString()) }) {
            Text("Iniciar Sesion")
        }

        if (viewModel.loginError){
            Text(viewModel.errorDescription, color= Color.Red, fontStyle = FontStyle.Italic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen(){
    LoginScreen(PaddingValues(), {})
}
