package adel.mendez.pokedex_compose.model.data.sqlite

import android.provider.BaseColumns

object Contract {
    // cada objeto creado es una tabla nueva en sqlite

    object Pokemons : BaseColumns {
        const val TABLE_NAME = "pokemons"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_NUMBER = "number"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_HEIGHT = "height"
        const val COLUMN_NAME_WEIGHT = "weight"
        const val COLUMN_NAME_FAVORITE = "favorite"
        const val COLUMN_NAME_ABILITY = "ability"
        const val COLUMN_NAME_IMAGE = "image"
    }
}
