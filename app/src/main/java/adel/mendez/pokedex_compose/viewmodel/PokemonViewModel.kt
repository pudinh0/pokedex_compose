package adel.mendez.pokedex_compose.viewmodel;

import adel.mendez.pokedex_compose.model.data.pokemonList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


import adel.mendez.pokedex_compose.model.domain.Pokemon;

public class PokemonViewModel : ViewModel(){
    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set


    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonedPokemon by mutableStateOf(false)
        private set


    fun searchPokemon(){
        gonedPokemon = false
        captured=false
        wildPokemon = pokemonList.random()
    }

    var captured by mutableStateOf(true)

    fun capturePokemon(){
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if(isCaptured == 1){
                capturedPokemon = capturedPokemon + it
                gonedPokemon = false
                wildPokemon = null
                captured = true
            }else {
                gonedPokemon= true
                wildPokemon=null
                captured = false;
            }
        }
    }


    // Función para actualizar el pokémon si lo necesitas
    fun selectPokemon(pokemon:Pokemon) {
        wildPokemon = pokemon
    }
}
