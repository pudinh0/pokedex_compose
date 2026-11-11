package adel.mendez.pokedex_compose.navigation

import adel.mendez.pokedex_compose.view.screens.LoginScreen
import adel.mendez.pokedex_compose.view.screens.MenuPokedexScreen
import adel.mendez.pokedex_compose.view.screens.PokemonDetailScreen
import adel.mendez.pokedex_compose.view.screens.PokemonHuntScreen
import adel.mendez.pokedex_compose.view.screens.ProfileScreen
import adel.mendez.pokedex_compose.viewmodel.PokemonDetailViewModel
import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun MyApp(innerPadding: PaddingValues, userViewModel: UserViewModel
, pokemonDetailViewModel: PokemonDetailViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = LoginScreen) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding,
                { pokemon ->
                    navController.navigate(PokemonDetail(pokemon))
                }, onNavigateToHunt = { navController.navigate(PokemonHunt) }, onLogout = {
                    userViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }, showGrid = userViewModel.showPokemonGrid,
                onGridChange = userViewModel::setPokemonGridPreference,
                onNavigateToProfile = { navController.navigate(Profile) }
            )
        }

        composable<PokemonDetail> { entry ->
            val route = entry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                innerPadding,
                route.pokemon,
                onNavigateToDetail = { id -> navController.navigate(route = PokemonDetail(id)) },
                pokemonDetailViewModel = pokemonDetailViewModel
            )
        }

        composable<PokemonHunt>{
            PokemonHuntScreen(innerPadding, onNavigateToDetail = {id-> navController.navigate(route= PokemonDetail(id))})
        }

        composable<LoginScreen>{
            LoginScreen(innerPadding, onNavigateToMenu = {navController.navigate(PokemonList)}, userViewModel)
        }

        composable<Profile>{
            ProfileScreen(innerPadding, userViewModel, onBack = { navController.popBackStack() })
        }


    }
}
