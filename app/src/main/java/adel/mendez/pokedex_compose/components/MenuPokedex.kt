package adel.mendez.pokedex_compose.components

import adel.mendez.pokedex_compose.data.pokemonList
import adel.mendez.pokedex_compose.domain.Pokemon
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp




    @Composable
    fun MenuPokedex(pokemonList: List<Pokemon>, innerPadding: PaddingValues){
        LazyColumn() {
            items(pokemonList){ pokemon ->
                PokemonRow(pokemon)

            }
        }

    }



    @Preview(showBackground = true)
    @Composable
    fun previewMenuPokedex(){
        MenuPokedex(pokemonList, PaddingValues(5.dp, 5.dp))
    }


