package adel.mendez.pokedex_compose.view.components

import adel.mendez.pokedex_compose.model.data.favoriteList
import adel.mendez.pokedex_compose.model.domain.Pokemon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, onNavigateToDetail: (id:Int) -> Unit) {
    LazyColumn() {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Composable
fun PokemonGrid(pokemonList: List<Pokemon>, onNavigateToDetail: (id:Int) -> Unit){
    LazyVerticalGrid(
        GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp, 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(pokemonList){
                pokemon ->
            PokemonCell(pokemon)
        }
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>, onNavigateToDetail: (id:Int) -> Unit){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewMenuPokedex() {
    //MenuPokedex(pokemonList = pokemonList, innerPadding = PaddingValues(5.dp))
    //FavoritesRow(favoriteList = favoriteList)
    //PokemonGrid(favoriteList)
}