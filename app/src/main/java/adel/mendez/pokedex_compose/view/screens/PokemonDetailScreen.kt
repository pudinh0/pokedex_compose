package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.R
import adel.mendez.pokedex_compose.model.data.getPokemonByNumber
import adel.mendez.pokedex_compose.model.data.pokemonList
import adel.mendez.pokedex_compose.model.domain.Pokemon
import adel.mendez.pokedex_compose.utilities.getColorByType
import adel.mendez.pokedex_compose.viewmodel.PokemonDetailViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Int, onNavigateToDetail: (Int) -> Unit,
                        pokemonDetailViewModel: PokemonDetailViewModel){

    val pokemonActual = getPokemonByNumber(pokemon)
    if (pokemonActual == null) {
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Pokémon no encontrado")
        }
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0xFFD0CEBE))
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f),
                pokemonActual
            )
            Caracteristicas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f),
                pokemonActual
            )
            EvolutionSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.20f),
                pokemonActual,
                onPokemonClick = { newPokemonNum ->
                    onNavigateToDetail(newPokemonNum)
                }
            )
        }




        Image(
            painter = painterResource(pokemonActual.image),
            contentDescription = "${pokemonActual.name} flotando",
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopCenter)
                .offset(x=15.dp,y = 100.dp)
        )
        Button({pokemonDetailViewModel.saveCompanion(pokemonActual)}) {
            Text("Seleccionar compañere")
        }

    }
}

@Composable
fun Header(modifier: Modifier = Modifier, pokemon : Pokemon) {
    Box(
        modifier = modifier
            .background(getColorByType(pokemon.type).first)
            .padding(24.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Separa los textos a un lado y la imagen al otro
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.align(Alignment.Top)
            ) {
                Text(
                    text = ("${pokemon.name}"),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = getColorByType(pokemon.type).second
                )
                Text(
                    text = ("#${pokemon.num}"),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = getColorByType(pokemon.type).second
                )
            }

            Image(
                painter = painterResource(id = R.drawable.outline_award_star_24),
                contentDescription = "Pokeball Vector",
                modifier = Modifier.size(40.dp)
                    .clickable{
                        pokemon.fav= !pokemon.fav
                    }
            )
        }


        Image(
            painter = painterResource(id = R.drawable.pokeball_icon),
            contentDescription = "pokeball",
            modifier = Modifier
                .size(500.dp)
                .align(Alignment.TopCenter)
                .offset(x = 120.dp, y = 100.dp)
        )

    }
}

@Composable
fun Caracteristicas(modifier: Modifier = Modifier,  pokemon : Pokemon){
    Column(
        modifier = modifier.fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(24.dp)
            .fillMaxHeight(0.5f)
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))

    ){
        Box(
            modifier = Modifier
                .background(getColorByType(pokemon.type).first, shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(pokemon.type, color = getColorByType(pokemon.type).second, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem(label = "Altura", "${pokemon.height}")
            StatItem(label = "Habilidad", pokemon.ability)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem(label = "Peso", "${pokemon.weight}")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = pokemon.description,
            fontSize = 20.sp,
            color = Color(0xFF424242),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Composable
fun StatItem(label: String, value: String) {
    Column {
        Text(text = label, fontWeight = FontWeight.Bold, color = Color(0xFF9E9D24), fontSize = 18.sp)
        Text(text = value, fontSize = 22.sp, color = Color(0xFF212121))
    }
}

@Composable
fun EvolutionSection(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onPokemonClick: (Int) -> Unit
) {
    val currentIndex = pokemonList.indexOf(pokemon)
    val pokemon1 = if (currentIndex > 0) pokemonList[currentIndex - 1] else null
    val pokemon2 = if (currentIndex < pokemonList.size - 1) pokemonList[currentIndex + 1] else null

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Pokémon Anterior
        if (pokemon1 != null) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .size(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier= Modifier.clickable { onPokemonClick(pokemon1.num) }
                ) {
                    Image(
                        painter = painterResource(id = pokemon1.image),
                        contentDescription = pokemon1.name,
                        modifier = Modifier.size(90.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row() {
                        Text(text = pokemon1.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = "#${pokemon1.num}", fontSize = 16.sp, color = Color.Black)

                    }
                }

            }
        } else {
            Spacer(modifier = Modifier.width(1.dp))
        }

        Spacer(modifier = Modifier.width(80.dp))

        // Pokémon Siguiente
        if (pokemon2 != null) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .size(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier= Modifier.clickable { onPokemonClick(pokemon2.num) }) {
                    Image(
                        painter = painterResource(id = pokemon2.image),
                        contentDescription = pokemon2.name,
                        modifier = Modifier.size(90.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(text = pokemon2.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = "#${pokemon2.num}", fontSize = 16.sp, color = Color.Black)
                    }
                }


            }
        } else {
            Spacer(modifier = Modifier.width(1.dp))
        }
    }
}
