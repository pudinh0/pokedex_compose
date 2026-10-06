package adel.mendez.pokedex_compose.navigation

import adel.mendez.pokedex_compose.domain.Pokemon
import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val pokemon: Int)