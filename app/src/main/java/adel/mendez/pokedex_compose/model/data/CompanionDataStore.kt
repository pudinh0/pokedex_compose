package adel.mendez.pokedex_compose.model.data

import adel.mendez.pokedex_compose.model.domain.Pokemon
import android.content.Context
import kotlinx.coroutines.flow.Flow

val Context.dataStore; DataStore<Pokemon> by dataStore(
    fileName = "pokemon.json"
    serializer = PkemonSerializer,
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