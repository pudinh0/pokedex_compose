package adel.mendez.pokedex_compose.components

import adel.mendez.pokedex_compose.data.jigglypuff
import adel.mendez.pokedex_compose.domain.Pokemon
import adel.mendez.pokedex_compose.ui.theme.verde40
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PokemonRow(pokemon: Pokemon){
    Row(
        Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                Modifier.padding().width(80.dp).padding(10.dp)
            )

        Column(
            Modifier.fillMaxWidth(0.70f), verticalArrangement  = Arrangement.spacedBy(2.dp)
        ){
            Text(pokemon.name, style = MaterialTheme.typography.labelLarge)
            Text(pokemon.description, fontSize = 10.sp)

            Row(
                Modifier.fillMaxWidth(.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Height:${pokemon.height}", style= MaterialTheme.typography.labelMedium)
                Text("Weight:${pokemon.weight}", style= MaterialTheme.typography.labelMedium)
            }

        }


        Text("${pokemon.num}", modifier = Modifier
            .background(color= verde40,
            shape = CircleShape)

            .padding(horizontal = 5.dp, vertical = 2.dp)
            )
        }
    }

@Preview(showBackground = true)

@Composable
fun PokemonElementPreview(){
    PokemonRow(jigglypuff)
}

