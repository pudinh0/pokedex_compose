package adel.mendez.pokedex_compose

import adel.mendez.pokedex_compose.view.components.MenuPokedex
import adel.mendez.pokedex_compose.model.data.pokemonList
import adel.mendez.pokedex_compose.navigation.MyApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import adel.mendez.pokedex_compose.ui.theme.Pokedex_composeTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pokedex_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // MenuPokedex(
                     //   pokemonList = pokemonList,
                       // innerPadding = innerPadding
                   // )
                    MyApp(innerPadding)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Pokedex_composeTheme {
        MenuPokedex(pokemonList = pokemonList,{})
    }
}