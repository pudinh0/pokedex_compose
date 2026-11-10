package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    val name = if (userViewModel.currentUser != null) userViewModel.currentUser!!.name else ""
    val email = if (userViewModel.currentUser != null) userViewModel.currentUser!!.email else ""

    val pokemon by userViewModel.pokemonCompanion.collectAsStateWithLifecycle()


    Column(Modifier.fillMaxWidth()
        .padding(innerPadding)
        .padding(top= 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("${name}")
        //se agrega la imagen
        //Image(painterResource(pokemon.image), )
        Image(painterResource(pokemon.image),
            contentDescription = "${pokemon.name} imagen")

        //crear un icono en xml coomo vector para el perfil
        Text(pokemon.name)
    }

}