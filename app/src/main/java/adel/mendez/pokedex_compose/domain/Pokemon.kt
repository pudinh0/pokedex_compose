package adel.mendez.pokedex_compose.domain

import adel.mendez.pokedex_compose.data.pokemonList

data class Pokemon(
    val name : String,
    val num: Int,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    val fav: Boolean,
    val ability: String,
    val image: Int
)