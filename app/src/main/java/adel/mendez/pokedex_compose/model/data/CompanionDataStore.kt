package adel.mendez.pokedex_compose.model.data

import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.model.preferences.PokemonSerializer
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Pokemon> by dataStore(
    fileName = "pokemon.json",
    serializer = PokemonSerializer,
    scope = CoroutineScope(Dispatchers.IO)
)
class CompanionDataStore(private val context: Context) {
    private val preferences = context.getSharedPreferences("companion_preferences", Context.MODE_PRIVATE)

    suspend fun saveCompanion(companion: Pokemon){
        context.dataStore.updateData{pokemon ->
            companion
        }
        preferences.edit().putLong(COMPANION_SELECTED_AT, System.currentTimeMillis()).apply()
    }

    fun pokemonCompany(): Flow<Pokemon> =
        context.dataStore.data.map {pokemon -> pokemon}

    fun companionSelectedAtMillis(): Long? =
        if (preferences.contains(COMPANION_SELECTED_AT)) {
            preferences.getLong(COMPANION_SELECTED_AT, 0L)
        } else null

    private companion object {
        const val COMPANION_SELECTED_AT = "companion_selected_at"
    }
}
