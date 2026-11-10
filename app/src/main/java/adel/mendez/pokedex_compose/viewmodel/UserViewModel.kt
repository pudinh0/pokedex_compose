package adel.mendez.pokedex_compose.viewmodel

import adel.mendez.pokedex_compose.model.data.CompanionDataStore
import adel.mendez.pokedex_compose.model.data.Users
import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.model.domain.User
import adel.mendez.pokedex_compose.model.preferences.PokemonSerializer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel(val users: Users,
    private val companionDataStore: CompanionDataStore): ViewModel() {
    var currentUser by mutableStateOf<User?>(null)
    var showPokemonGrid by mutableStateOf(users.shouldShowPokemonGrid())
    init{
        currentUser = users.getCurrentUser()
    }
    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")

    val pokemonCompanion: StateFlow<Pokemon> = companionDataStore.pokemonCompany()
        .stateIn(
            scope = viewModelScope,
            started= SharingStarted.WhileSubscribed(5000),
            initialValue= PokemonSerializer.defaultValue
        )

    fun login(user: String, password: String){
        val user = users.loginService(user, password)
        if (user == null){
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
        }else{
            loginError = false
            errorDescription = ""
            currentUser = user
        }
    }

    fun isLoggedIn(): Boolean{
        return users.isLoggedIn()
    }

    fun logout(){
        users.logout()
        currentUser = null
    }

    fun setPokemonGridPreference(showGrid: Boolean) {
        showPokemonGrid = showGrid
        users.savePokemonDisplayAsGrid(showGrid)
    }
}
