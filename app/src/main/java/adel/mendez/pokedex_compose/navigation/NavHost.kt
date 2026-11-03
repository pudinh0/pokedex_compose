package adel.mendez.pokedex_compose.navigation

import adel.mendez.pokedex_compose.view.screens.LoginScreen
import adel.mendez.pokedex_compose.view.screens.MenuPokedexScreen
import adel.mendez.pokedex_compose.view.screens.PokemonDetailScreen
import adel.mendez.pokedex_compose.view.screens.PokemonHuntScreen
import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = LoginScreen) {
        composable<PokemonList> {
            MenuPokedexScreen(innerPadding,
                {pokemon ->
                    navController.navigate(PokemonDetail(pokemon))
                }, onNavigateToHunt = {navController.navigate(PokemonHunt)}
            , onLogout = {
                userViewModel.logout()
                navController.navigate(LoginScreen) {
                    popUpTo(navController.graph.id) { inclusive = true }
                    launchSingleTop = true
                }
            }, showGrid = userViewModel.showPokemonGrid,
                onGridChange = userViewModel::setPokemonGridPreference)
        }

        composable<PokemonDetail> {
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDetailScreen(innerPadding, pokemon,onNavigateToDetail = {id-> navController.navigate(route= PokemonDetail(id))})
        }

        composable<PokemonHunt>{
            PokemonHuntScreen(innerPadding, onNavigateToDetail = {id-> navController.navigate(route= PokemonDetail(id))})
        }

        composable<LoginScreen>{
            LoginScreen(innerPadding, onNavigateToMenu = {navController.navigate(PokemonList)}, userViewModel)
        }


    }
}
