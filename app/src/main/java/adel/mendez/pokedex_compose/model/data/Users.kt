package adel.mendez.pokedex_compose.model.data

import adel.mendez.pokedex_compose.model.domain.User
import adel.mendez.pokedex_compose.model.preferences.ARCHIVO
import adel.mendez.pokedex_compose.model.preferences.USER
import adel.mendez.pokedex_compose.model.preferences.USERNAME
import adel.mendez.pokedex_compose.model.preferences.POKEMON_DISPLAY
import android.content.Context

class Users(val context: Context){
    val users = listOf<User>(
        User("popusa@gmail.com", "123456", "Popusita Mendez"),
        User("NormaTronquilloBeltran@gmail.com","789123", "Norma Beltran"),
        User("DenisSinE@gmail.com", "456789","Denis")
    )

    fun getUserByMail(mail:String?): User?{
        return users.firstOrNull{
            it.email.equals(mail, ignoreCase = true)
        }
    }

    fun loginService(mail:String, password:String): User?{
        val user = getUserByMail(mail)
        if (user?.password == password) {
            saveUser(user.email, user.name)
            return user
        }
        return null
    }

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun saveUser(user: String, name: String){
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun getCurrentUser(): User? {
        val user = shared.getString( USER, null )
        return getUserByMail(user)
    }

    fun logout(){
        val editor = shared.edit()
        editor.putString(USER, null)
        editor.putString(USERNAME, null)
        editor.apply()
    }

    fun isLoggedIn(): Boolean{
        return getCurrentUser() != null
    }

    fun shouldShowPokemonGrid(): Boolean {
        return shared.getBoolean(POKEMON_DISPLAY, false)
    }

    fun savePokemonDisplayAsGrid(showGrid: Boolean) {
        shared.edit().putBoolean(POKEMON_DISPLAY, showGrid).apply()
    }


}
