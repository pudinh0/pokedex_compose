package adel.mendez.pokedex_compose.model

import adel.mendez.pokedex_compose.model.domain.Pokemon

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCaptured: Pokemon? = null
)