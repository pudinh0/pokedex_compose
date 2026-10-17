package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.ui.theme.Psych
import adel.mendez.pokedex_compose.ui.theme.Purple40
import adel.mendez.pokedex_compose.ui.theme.Typography
import adel.mendez.pokedex_compose.view.components.PokemonRow
import adel.mendez.pokedex_compose.viewmodel.PokemonViewModel
import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel(), onNavigateToDetail: (id:Int)-> Unit){
    Spacer(Modifier.size(30.dp))

    Column(
        modifier = Modifier.fillMaxWidth().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {viewModel.searchPokemon() }) {
            Text("Buscar pokemon en la hierva")
        }

        Spacer(modifier = Modifier.size(18.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Text("Apareció un ${pokemon.name} salvaje!", style = MaterialTheme.typography.labelLarge, color = Purple40)

            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.size(15.dp))

            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}!")
            }


        }


        if(viewModel.captured){
            Text("Pokemon capturados", style = Typography.labelLarge, color = Purple40)
        }

        if(viewModel.gonedPokemon){
            Text("se escapo", style = Typography.labelLarge, color = Purple40)
            Thread.sleep(2000)
            viewModel.captured= false
        }

        Spacer(Modifier.size(20.dp))

        Text("Pokemones capturados", style= MaterialTheme.typography.titleLarge, color= Psych)

        LazyColumn() {
            items(viewModel.capturedPokemon){
                    pokemon ->
                PokemonRow(pokemon, onNavigateToDetail)
            }
            
        }
    }
}

@Preview(showBackground = true)
@Composable

fun previewPokemonHuntScreen(){
    PokemonHuntScreen(PaddingValues(15.dp), onNavigateToDetail = {id -> Unit})
}