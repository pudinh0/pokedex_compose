package adel.mendez.pokedex_compose.model.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name : String,
    val num: Int,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    var fav: Boolean,
    val ability: String,
    val image: Int
)