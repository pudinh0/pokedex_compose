package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.R
import adel.mendez.pokedex_compose.model.data.getFavoritePokemons
import adel.mendez.pokedex_compose.model.data.pokemonList
import adel.mendez.pokedex_compose.ui.theme.Blue
import adel.mendez.pokedex_compose.ui.theme.Green
import adel.mendez.pokedex_compose.ui.theme.LightBlue
import adel.mendez.pokedex_compose.ui.theme.LightGreen
import adel.mendez.pokedex_compose.ui.theme.Pokedex_composeTheme
import adel.mendez.pokedex_compose.ui.theme.Purple40
import adel.mendez.pokedex_compose.view.components.FavoritePokemon
import adel.mendez.pokedex_compose.view.components.MenuPokedex
import adel.mendez.pokedex_compose.view.components.PokemonGrid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues,
                      onNavigateToDetail: (pokemon:Int) -> Unit,
                      onNavigateToHunt:() -> Unit, onLogout:()-> Unit,
                      showGrid: Boolean = false,
                      onGridChange: (Boolean) -> Unit = {},
                      onNavigateToProfile: () -> Unit

) {
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        floatingActionButton = {

            FloatingActionButton(
                onClick = onNavigateToHunt,
                containerColor = Purple40,
                contentColor = Color.White
            ) {
                Text("Atrapar",
                    modifier = Modifier.padding(horizontal = 15.dp))
            }
        }
    ) { contentPadding ->
      Column(
        modifier = Modifier.fillMaxSize().padding(contentPadding).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        OutlinedButton(onClick = onNavigateToProfile) {
            Text("Mi perfil")
        }


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(15.dp)
        ) {
            Text(
                text = "Mis Favoritos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(onClick = onLogout ){
                Text("Cerrar sesión")
            }
        }



        //Fila de pokemones favs
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //cambiar por el getfavorites
            items(getFavoritePokemons()) { pokemon ->
                FavoritePokemon(pokemon = pokemon, onNavigateToDetail)
            }
        }

        //grid de todso los pokemones

        Row (horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Todos mis pokemones",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(80.dp))
            Switch(
                checked = showGrid,
                onCheckedChange = onGridChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Green,
                    checkedTrackColor = LightGreen,
                    uncheckedThumbColor = Blue,
                    uncheckedTrackColor = LightBlue,
                    uncheckedBorderColor = Color.Transparent
                ),
                thumbContent = if (showGrid) {
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
        }


        if (showGrid) {
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
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    Pokedex_composeTheme {
      MenuPokedexScreen(
          innerPadding = PaddingValues(0.dp),
          onNavigateToDetail = {},
          onNavigateToHunt = {},
          onLogout = {},
          onNavigateToProfile = {})
    }
}
