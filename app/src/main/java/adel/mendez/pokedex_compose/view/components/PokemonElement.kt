package adel.mendez.pokedex_compose.view.components

import adel.mendez.pokedex_compose.model.data.jigglypuff
import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.ui.theme.OffWhite
import adel.mendez.pokedex_compose.utilities.getColorByType
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PokemonRow(pokemon: Pokemon){
    val colors = getColorByType(pokemon.type)

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

        NumberChip(text ="${pokemon.num}", colors = colors)


        }
    }

@Composable
fun FavoritePokemon(pokemon: Pokemon){
    val colors = getColorByType(pokemon.type)

    Column(
       modifier = Modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
           modifier =  Modifier.width(100.dp),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier

                    .border(
                        BorderStroke(
                            width = 5.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    colors.first,
                                    OffWhite,
                                    colors.first,
                                    OffWhite,
                                    colors.first
                                )
                            )
                        ),
                    ),
                contentAlignment = Alignment.Center
            ){
                //image
                Image(
                    painterResource(pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier.padding(5.dp).width(75.dp)


                )
            }

            NumberChip(
                text = "${pokemon.num}",
                colors = colors,
                modifier = Modifier.offset(x=40.dp, y = 40.dp)
            )

        }
        Text(
            text = pokemon.name,
            style= MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon){
    val colors = getColorByType(pokemon.type)

    Column(
        modifier = Modifier
            .border(
            BorderStroke(
                width = 5.dp,
                brush = Brush.sweepGradient(
                    colors = listOf(
                        colors.first,
                        OffWhite,
                        colors.first,
                        OffWhite,
                        colors.first
                    )
                )

            )

        ).padding(5.dp)
            //falto agregar la funcion de navigate on detail en cada parte,
        // esa funcion se encarga de la navegacion y pasa el number/int del pokemon a la siguiente pantalla
           // .clickable(true, onClick = {onNavigateToDetail(pokemon.num)})
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ){
            Box(
             modifier = Modifier
                 ,
                contentAlignment = Alignment.Center
            ){

                Image(
                    painterResource(pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier.padding(10.dp).width(150.dp)

                )
            }

            NumberChip(
                text = "${pokemon.num}",
                colors = colors
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}


@Preview(showBackground = true)

@Composable
fun PokemonElementPreview(){
    //PokemonCell(jigglypuff)
    //PokemonRow(jigglypuff)
    FavoritePokemon(jigglypuff)
}

