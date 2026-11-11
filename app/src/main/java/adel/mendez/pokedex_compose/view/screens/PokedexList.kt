package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.model.data.Users
import adel.mendez.pokedex_compose.model.data.CompanionDataStore
import adel.mendez.pokedex_compose.model.data.PokemonRepository
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
import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import adel.mendez.pokedex_compose.viewmodel.PokemonDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val users = Users(applicationContext)
        val companionDataStore = CompanionDataStore(applicationContext)
        val pokemonRepository = PokemonRepository()
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
                modelClass.isAssignableFrom(UserViewModel::class.java) ->
                    UserViewModel(users, companionDataStore) as T
                modelClass.isAssignableFrom(PokemonDetailViewModel::class.java) ->
                    PokemonDetailViewModel(pokemonRepository, companionDataStore) as T
                else -> throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
            }
        }
        val userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        val pokemonDetailViewModel = ViewModelProvider(this, factory)[PokemonDetailViewModel::class.java]
        setContent {
            Pokedex_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(innerPadding, userViewModel, pokemonDetailViewModel)
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
