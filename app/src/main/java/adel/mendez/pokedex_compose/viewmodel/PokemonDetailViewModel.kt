package adel.mendez.pokedex_compose.viewmodel

import adel.mendez.pokedex_compose.model.data.CompanionDataStore
import adel.mendez.pokedex_compose.model.domain.Pokemon
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val companionDataStore: CompanionDataStore) : ViewModel() {

    fun saveCompanion(pokemon: Pokemon){
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
        }
    }
}