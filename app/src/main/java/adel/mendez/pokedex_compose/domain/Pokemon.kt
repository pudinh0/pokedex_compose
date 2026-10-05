package adel.mendez.pokedex_compose.domain

data class Pokemon(
    val name : String,
    val num: Number,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    val fav: Boolean,
    val ability: String,
    val image: Int
)
