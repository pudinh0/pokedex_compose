package adel.mendez.pokedex_compose.utilities

import adel.mendez.pokedex_compose.ui.theme.Bug
import adel.mendez.pokedex_compose.ui.theme.Dark
import adel.mendez.pokedex_compose.ui.theme.DarkGray
import adel.mendez.pokedex_compose.ui.theme.Dragon
import adel.mendez.pokedex_compose.ui.theme.Electric
import adel.mendez.pokedex_compose.ui.theme.Fairy
import adel.mendez.pokedex_compose.ui.theme.Fight
import adel.mendez.pokedex_compose.ui.theme.Fire
import adel.mendez.pokedex_compose.ui.theme.Flying
import adel.mendez.pokedex_compose.ui.theme.Ghost
import adel.mendez.pokedex_compose.ui.theme.Grass
import adel.mendez.pokedex_compose.ui.theme.Ground
import adel.mendez.pokedex_compose.ui.theme.Ice
import adel.mendez.pokedex_compose.ui.theme.Normal
import adel.mendez.pokedex_compose.ui.theme.OffWhite
import adel.mendez.pokedex_compose.ui.theme.Poison
import adel.mendez.pokedex_compose.ui.theme.Psych
import adel.mendez.pokedex_compose.ui.theme.Rock
import adel.mendez.pokedex_compose.ui.theme.Water
import android.graphics.Color


fun getColorByType(type: String): Pair<androidx.compose.ui.graphics.Color, androidx.compose.ui.graphics.Color> {
    return when (type.lowercase()) {
        "normal" -> Pair(Normal, OffWhite)
        "water" -> Pair(Water, OffWhite)
        "fire" -> Pair(Fire, OffWhite)
        "psych", "psychic" -> Pair(Psych, OffWhite)
        "ghost" -> Pair(Ghost, OffWhite)
        "bug" -> Pair(Bug, OffWhite)
        "poison" -> Pair(Poison, OffWhite)
        "grass" -> Pair(Grass, OffWhite)
        "ground" -> Pair(Ground, OffWhite)
        "rock" -> Pair(Rock, OffWhite)

        "electric" -> Pair(Electric, DarkGray)
        "fairy" -> Pair(Fairy, DarkGray)
        "fight", "fighting" -> Pair(Fight, DarkGray)
        "flying" -> Pair(Flying, DarkGray)

        else -> Pair(Normal, DarkGray) // Valor por defecto en caso de no coincidir
    }
}