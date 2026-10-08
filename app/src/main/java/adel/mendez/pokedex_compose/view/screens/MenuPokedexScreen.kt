package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.view.components.FavoritePokemon
import adel.mendez.pokedex_compose.view.components.PokemonGrid
import adel.mendez.pokedex_compose.model.data.getAllPokemons
import adel.mendez.pokedex_compose.model.data.getFavoritePokemons
import adel.mendez.pokedex_compose.ui.theme.Pokedex_composeTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues, onNavigateToDetail: (pokemon:Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        //Fila de pokemones favs
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //cambiar por el getfavorites
            items(getFavoritePokemons()) { pokemon ->
                FavoritePokemon(pokemon = pokemon)
            }
        }

        //grid de todso los pokemones
        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        PokemonGrid(
            getAllPokemons()
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuPokedexScreenPreview() {
    Pokedex_composeTheme {
      //  MenuPokedexScreen(
        //    innerPadding = PaddingValues(0.dp)
        //)
    }
}