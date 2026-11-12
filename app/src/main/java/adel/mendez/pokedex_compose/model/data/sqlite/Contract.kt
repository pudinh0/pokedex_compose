package adel.mendez.pokedex_compose.model.data.sqlite

import android.provider.BaseColumns

object Contract {
    // cada objeto creado es una tabla nueva en sqlite

    // terminar de agregar cada atributos del pokemon
    object Pokemons : BaseColumns{
        const val TABLE_NAME = "pokemons"
        const val COLUMN_NAME_ID= "_id"
        const val COLUMN_NAME_NUMBER = "number"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_DESCRIPTION = "description"
    }
}