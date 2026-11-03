package adel.mendez.pokedex_compose.viewmodel

import adel.mendez.pokedex_compose.model.data.Users
import adel.mendez.pokedex_compose.model.domain.User
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserViewModel(val users: Users) {
    var currentUser by mutableStateOf<User?>(null)
    var showPokemonGrid by mutableStateOf(users.shouldShowPokemonGrid())
    init{
        currentUser = users.getCurrentUser()
    }
    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")

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
