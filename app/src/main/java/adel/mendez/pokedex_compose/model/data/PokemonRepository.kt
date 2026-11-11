package adel.mendez.pokedex_compose.model.data

import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.model.data.getPokemonByNumber as findPokemonByNumber

class PokemonRepository {
    fun getPokemonByNumber(number: Int): Pokemon? =
        findPokemonByNumber(number)

    fun getAdjacentPokemons(number: Int): Pair<Pokemon?, Pokemon?> {
        val index = getAllPokemons().indexOfFirst { it.num == number }
        if (index < 0) return null to null
        val pokemons = getAllPokemons()
        return pokemons.getOrNull(index - 1) to pokemons.getOrNull(index + 1)
    }
}
