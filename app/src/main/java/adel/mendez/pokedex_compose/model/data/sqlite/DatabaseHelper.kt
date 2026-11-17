package adel.mendez.pokedex_compose.model.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createPokemons = """
            CREATE TABLE ${Contract.Pokemons.TABLE_NAME} (
                ${Contract.Pokemons.COLUMN_NAME_ID} INTEGER PRIMARY KEY,
                ${Contract.Pokemons.COLUMN_NAME_NUMBER} INTEGER NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_NAME} TEXT NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_DESCRIPTION} TEXT NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_TYPE} TEXT NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_HEIGHT} REAL NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_WEIGHT} REAL NOT NULL,
                ${Contract.Pokemons.COLUMN_NAME_FAVORITE} INTEGER NOT NULL DEFAULT 0,
                ${Contract.Pokemons.COLUMN_NAME_ABILITY} TEXT NOT NULL DEFAULT '',
                ${Contract.Pokemons.COLUMN_NAME_IMAGE} INTEGER NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createPokemons)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS ${Contract.Pokemons.TABLE_NAME}")
        onCreate(db)
    }

    companion object{
        private const val DATABASE_NAME ="pokedex.db"
        //cuando se hagan cambios  en el contrato se cambia la version del database
        private const val DATABASE_VERSION=2

    }

}
