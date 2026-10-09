package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.R
import adel.mendez.pokedex_compose.model.data.getAllPokemons
import adel.mendez.pokedex_compose.model.data.getFavoritePokemons
import adel.mendez.pokedex_compose.model.data.pokemonList
import adel.mendez.pokedex_compose.ui.theme.Blue
import adel.mendez.pokedex_compose.ui.theme.Green
import adel.mendez.pokedex_compose.ui.theme.LightBlue
import adel.mendez.pokedex_compose.ui.theme.LightGreen
import adel.mendez.pokedex_compose.ui.theme.Pokedex_composeTheme
import adel.mendez.pokedex_compose.view.components.FavoritePokemon
import adel.mendez.pokedex_compose.view.components.MenuPokedex
import adel.mendez.pokedex_compose.view.components.PokemonGrid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues, onNavigateToDetail: (pokemon:Int) -> Unit) {
    var grid by remember {
        mutableStateOf(false)
    }

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

        Switch(
            checked = grid,
            onCheckedChange = { grid = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Green,
                checkedTrackColor = LightGreen,
                uncheckedThumbColor = Blue,
                uncheckedTrackColor = LightBlue,
                uncheckedBorderColor = Color.Transparent
            ),
            thumbContent = if (grid) {
                {
                    Icon(
                        painter = painterResource(id = R.drawable.opt),
                        contentDescription = "Grid Icon",
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            } else {
                {
                    Icon(
                        painterResource(id = R.drawable.list),
                        contentDescription = "List icon",
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )

                }
            }
        )

        if (grid) {
            PokemonGrid(
                pokemonList = pokemonList,
                onNavigateToDetail = onNavigateToDetail
            )
        } else {
            MenuPokedex(
                pokemonList = pokemonList,
                onNavigateToDetail
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    Pokedex_composeTheme {
      MenuPokedexScreen(
           innerPadding = PaddingValues(0.dp),
          { })


    }
}