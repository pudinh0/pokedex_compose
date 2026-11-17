package adel.mendez.pokedex_compose.viewmodel

import adel.mendez.pokedex_compose.R
import adel.mendez.pokedex_compose.model.data.interfaces.IPokemonRepository
import adel.mendez.pokedex_compose.model.domain.Pokemon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPokemonViewModel(private val pokemonRepository: IPokemonRepository) : ViewModel() {
    private val pokemonsMutable = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons = pokemonsMutable.asStateFlow()

    var pokemonSaved by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            try {
                pokemonsMutable.value = pokemonRepository.getPokemons()
                errorMessage = null
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (error: Exception) {
                errorMessage = error.localizedMessage ?: "No se pudieron cargar los Pokémon."
            }
        }
    }

    fun savePokemon(name: String, type: String, number: Int, description: String) {
        pokemonSaved = false
        errorMessage = null
        val pokemon = Pokemon(
            name = name.trim(),
            num = number,
            type = type,
            description = description.trim(),
            height = 0f,
            weight = 0f,
            fav = false,
            ability = "",
            image = R.drawable.wtp
        )

        viewModelScope.launch {
            try {
                pokemonRepository.addPokemon(pokemon)
                pokemonSaved = true
                pokemonsMutable.value = pokemonRepository.getPokemons()
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (error: Exception) {
                errorMessage = error.localizedMessage ?: "No se pudo guardar el Pokémon."
            }
        }
    }
}
