package adel.mendez.pokedex_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import adel.mendez.pokedex_compose.ui.theme.Pokedex_composeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pokedex_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFFFFEB3B))
            .padding(24.dp)
    ) {
        // Capa 1: Tus textos alineados a la izquierda (o centrados)
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(
                text = stringResource(id = R.string.nombre_dig),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )
            Text(
                text = stringResource(id = R.string.numero_dig),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF616161)
            )
        }

    }
}

@Composable
fun Caracteristicas(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(24.dp)
            .fillMaxHeight(0.5f)
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))

    ){
        Box(
            modifier = Modifier
                .background(Color(0xFFBCAAA4), shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(text = stringResource(id = R.string.atributo), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem(label = "Altura", value = stringResource(id = R.string.altura_dig))
            StatItem(label = "Habilidad", value = stringResource(id = R.string.habilidad_dig))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem(label = "Peso", value = stringResource(id = R.string.peso_dig))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.descripcion_dig),
            fontSize = 15.sp,
            color = Color(0xFF424242),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun StatItem(label: String, value: String) {
    Column {
        Text(text = label, fontWeight = FontWeight.Bold, color = Color(0xFF9E9D24), fontSize = 14.sp)
        Text(text = value, fontSize = 18.sp, color = Color(0xFF212121))
    }
}

@Composable
fun EvolutionSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(24.dp)
            .fillMaxHeight(0.2f),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.dig_anterior), fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = stringResource(id = R.string.dig_siguiente), fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0CEBE))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f)
            )
            Caracteristicas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
            EvolutionSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.10f)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.dataicon_pic),
            contentDescription = "tipo digimon",
            modifier = Modifier
                .size(375.dp)
                .align(Alignment.TopCenter)
                .offset(x = 80.dp, y = -25.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.patamonprincipal_icon),
            contentDescription = "Patamon flotando",
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopCenter)
                .offset(y = 75.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.tokomon_pic),
            contentDescription = "tokomon flotando",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(x= -120.dp, y = 650.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.angemon_pic),
            contentDescription = "angemon flotando",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(x= 120.dp, y = 650.dp)
        )

    }
}