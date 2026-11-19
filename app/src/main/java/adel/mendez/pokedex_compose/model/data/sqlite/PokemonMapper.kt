package adel.mendez.pokedex_compose.model.data.sqlite

import android.content.ContentValues
import android.database.Cursor
import adel.mendez.pokedex_compose.model.domain.Pokemon
import androidx.core.database.getBlobOrNull

class PokemonMapper {
    fun getPokemon(cursor: Cursor): Pokemon = Pokemon(
        name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NAME)),
        num = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NUMBER)),
        type = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_TYPE)),
        description = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_DESCRIPTION)),
        // get int or nulll para heiht weight fav, ability
        height = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_HEIGHT)),
        weight = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_WEIGHT)),
        fav = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_FAVORITE)) != 0,
        ability = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_ABILITY)),
        // get blob or null
        // cambiar el image por el getblobOrNull
        image = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_IMAGE)),
        //imageSQL = image
    )

    fun getContentValues(pokemon: Pokemon): ContentValues = ContentValues().apply {
        put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.num)
        put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
        put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
        put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
        put(Contract.Pokemons.COLUMN_NAME_HEIGHT, pokemon.height)
        put(Contract.Pokemons.COLUMN_NAME_WEIGHT, pokemon.weight)
        put(Contract.Pokemons.COLUMN_NAME_FAVORITE, if (pokemon.fav) 1 else 0)
        put(Contract.Pokemons.COLUMN_NAME_ABILITY, pokemon.ability)
        put(Contract.Pokemons.COLUMN_NAME_IMAGE, pokemon.image)
    }
}
