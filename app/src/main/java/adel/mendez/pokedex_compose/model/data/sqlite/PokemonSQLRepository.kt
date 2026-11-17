package adel.mendez.pokedex_compose.model.data.sqlite

import adel.mendez.pokedex_compose.model.data.interfaces.IPokemonDAO
import adel.mendez.pokedex_compose.model.data.interfaces.IPokemonRepository
import adel.mendez.pokedex_compose.model.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {
    override suspend fun addPokemon(pokemon: Pokemon) {
        require(pokemon.num > 0) { "El número del Pokémon debe ser mayor que cero." }
        require(pokemon.name.isNotBlank()) { "El nombre del Pokémon no puede estar vacío." }
        require(pokemon.type.isNotBlank()) { "Selecciona un tipo para el Pokémon." }
        require(pokemon.description.isNotBlank()) { "La descripción no puede estar vacía." }
        dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> = dao.getAllPokemons()

    override suspend fun getFavoritePokemons(): List<Pokemon> = dao.getFavoritePokemons()

    override suspend fun getPokemonsByType(type: String): List<Pokemon> = dao.getPokemonsByType(type)

    override suspend fun updatePokemon(pokemon: Pokemon) {
        require(pokemon.num > 0) { "El número del Pokémon debe ser mayor que cero." }
        require(pokemon.name.isNotBlank()) { "El nombre del Pokémon no puede estar vacío." }
        require(pokemon.type.isNotBlank()) { "Selecciona un tipo para el Pokémon." }
        require(pokemon.description.isNotBlank()) { "La descripción no puede estar vacía." }
        dao.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        dao.getPokemonById(id)?.let { dao.deletePokemon(it) }
    }
}
