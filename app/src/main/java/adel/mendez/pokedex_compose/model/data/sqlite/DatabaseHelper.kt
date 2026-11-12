package adel.mendez.pokedex_compose.model.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    companion object{
        private const val DATABASE_NAME ="pokedex.db"
        //cuando se hagan cambios  en el contrato se cambia la version del database
        private const val DATABASE_VERSION=1

    }

}