package adel.mendez.pokedex_compose.navigation

import adel.mendez.pokedex_compose.screens.MenuPokedexScreen
import adel.mendez.pokedex_compose.screens.PokemonDeteailScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp(innerPadding: PaddingValues){
    val navController = rememberNavController()

    NavHost(navController, startDestination = PokemonList){
        composable<PokemonList>{
            MenuPokedexScreen(innerPadding, {pokemon -> navController.navigate(PokemonDetail(pokemon))})
        }

        composable<PokemonDetail>(){
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDeteailScreen(innerPadding, pokemon)
        }
    }

}