package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.viewmodel.PokemonViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {viewModel.searchPokemon() }) {
            Text("Buscar pokemon en la hierva")
        }

        Spacer(modifier = Modifier.size(18.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Text("Apareció un ${pokemon.name} salvaje!")

            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name}",
                modifier = Modifier
                    .height(90.dp)
                    .padding(20.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}!")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable

fun previewPokemonHuntScreen(){
    PokemonHuntScreen(PaddingValues(15.dp))
}