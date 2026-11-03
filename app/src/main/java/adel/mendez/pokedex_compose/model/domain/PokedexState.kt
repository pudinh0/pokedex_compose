package adel.mendez.pokedex_compose.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCaptured: Pokemon? = null
)