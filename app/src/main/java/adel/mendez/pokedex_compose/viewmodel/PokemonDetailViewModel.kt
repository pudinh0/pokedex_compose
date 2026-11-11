package adel.mendez.pokedex_compose.viewmodel

import adel.mendez.pokedex_compose.model.data.CompanionDataStore
import adel.mendez.pokedex_compose.model.data.PokemonRepository
import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.model.preferences.PokemonSerializer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonRepository: PokemonRepository,
    private val companionDataStore: CompanionDataStore
) : ViewModel() {
    var pokemon by mutableStateOf<Pokemon?>(null)
        private set
    var previousPokemon by mutableStateOf<Pokemon?>(null)
        private set
    var nextPokemon by mutableStateOf<Pokemon?>(null)
        private set
    var selectionMessage by mutableStateOf<String?>(null)
        private set

    val companion: StateFlow<Pokemon> = companionDataStore.pokemonCompany()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), PokemonSerializer.defaultValue)

    fun loadPokemon(number: Int) {
        pokemon = pokemonRepository.getPokemonByNumber(number)
        val (previous, next) = pokemonRepository.getAdjacentPokemons(number)
        previousPokemon = previous
        nextPokemon = next
        selectionMessage = null
    }

    fun saveCompanion(pokemon: Pokemon) {
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
            selectionMessage = "Compañero seleccionado."
        }
    }
}
