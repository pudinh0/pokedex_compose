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
    suspend fun saveCompanion(companion: Pokemon){
        context.dataStore.updateData{pokemon ->
            companion
        }
    }

    fun pokemonCompany(): Flow<Pokemon> =
        context.dataStore.data.map {pokemon -> pokemon}
}