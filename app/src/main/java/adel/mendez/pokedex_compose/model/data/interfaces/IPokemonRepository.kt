package adel.mendez.pokedex_compose.model.data.interfaces

import adel.mendez.pokedex_compose.model.domain.Pokemon

interface IPokemonRepository {
    suspend fun addPokemon(pokemon: Pokemon)
    suspend fun getPokemons(): List<Pokemon>
    suspend fun getFavoritePokemons(): List<Pokemon>
    suspend fun getPokemonsByType(type: String): List<Pokemon>
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun deletePokemon(id: Int)
}
