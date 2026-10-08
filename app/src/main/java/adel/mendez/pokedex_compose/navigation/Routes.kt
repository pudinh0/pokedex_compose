package adel.mendez.pokedex_compose.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
object PokemonList{
    fun serializer(): KSerializer<PokemonList>{}
}

@Serializable
data class PokemonDetail(val pokemon: Int){
    fun serializer(): KSerializer<PokemonDetail>{}
}