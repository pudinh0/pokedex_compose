package adel.mendez.pokedex_compose.model.data.sqlite

import adel.mendez.pokedex_compose.model.data.interfaces.IPokemonDAO
import adel.mendez.pokedex_compose.model.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDAO(private val dbHelper: DatabaseHelper) : IPokemonDAO {
    private val mapper = PokemonMapper()

    override suspend fun insertPokemon(pokemon: Pokemon) = withContext(Dispatchers.IO) {
        dbHelper.writableDatabase.insertOrThrow(
            Contract.Pokemons.TABLE_NAME,
            null,
            mapper.getContentValues(pokemon)
        )
        Unit
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} ORDER BY ${Contract.Pokemons.COLUMN_NAME_NUMBER} ASC"
            val cursor = db.rawQuery(query, null)
            cursor.use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return withContext(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} WHERE ${Contract.Pokemons.COLUMN_NAME_NUMBER} = ? LIMIT 1"
            val cursor = db.rawQuery(query, arrayOf(id.toString()))
            cursor.use { c -> if (c.moveToFirst()) mapper.getPokemon(c) else null }
        }
    }

    // The practice leaves the favorites query out of scope.
    override suspend fun getFavoritePokemons(): List<Pokemon> = emptyList()

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} WHERE ${Contract.Pokemons.COLUMN_NAME_TYPE} = ? ORDER BY ${Contract.Pokemons.COLUMN_NAME_NUMBER} ASC"
            val cursor = db.rawQuery(query, arrayOf(type))
            cursor.use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) = withContext(Dispatchers.IO) {
        dbHelper.writableDatabase.update(
            Contract.Pokemons.TABLE_NAME,
            mapper.getContentValues(pokemon),
            "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
            arrayOf(pokemon.num.toString())
        )
        Unit
    }

    override suspend fun deletePokemon(pokemon: Pokemon) = withContext(Dispatchers.IO) {
        dbHelper.writableDatabase.delete(
            Contract.Pokemons.TABLE_NAME,
            "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
            arrayOf(pokemon.num.toString())
        )
        Unit
    }
}
