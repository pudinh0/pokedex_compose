package adel.mendez.pokedex_compose.model.preferences

import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.R
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object PokemonSerializer : Serializer<Pokemon>{
    override val defaultValue: Pokemon = Pokemon(
       name = "",
        num = -1,
        type = "",
        description = "",
        height = 0f,
        weight = 0f,
        fav = false,
        ability = "",
        image = R.drawable.wtp
    )

    override suspend fun readFrom(input: InputStream): Pokemon {
       return try {
           Json.decodeFromString(
               Pokemon.serializer(), input.readBytes().decodeToString()
           )
       }catch (exception: SerializationException){
           throw CorruptionException("No se pudo eleer el json")
       }
    }

    override suspend fun writeTo(
        t: Pokemon,
        output: OutputStream
    ) {
        output.write(Json.encodeToString(Pokemon.serializer(), t)
            .encodeToByteArray()
        )
    }

}
