package adel.mendez.pokedex_compose.navigation


import kotlinx.serialization.Serializable

@Serializable
object PokemonList{
}

@Serializable
data class PokemonDetail(val pokemon: Int){
}

@Serializable
object PokemonHunt{

}

@Serializable
object LoginScreen

@Serializable
object Logout

@Serializable
object Profile