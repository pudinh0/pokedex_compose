package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.ui.theme.Purple40
import adel.mendez.pokedex_compose.viewmodel.UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    onBack: () -> Unit
) {
    val companion by userViewModel.pokemonCompanion.collectAsStateWithLifecycle()
    val user = userViewModel.currentUser
    val selectedAt = userViewModel.companionSelectedAtMillis()
    val daysTogether = selectedAt?.let { timestamp ->
        val selectedDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
        ChronoUnit.DAYS.between(selectedDate, LocalDate.now()).coerceAtLeast(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Brush.verticalGradient(listOf(Color(0xFFF3E8FF), Color(0xFFFAF8FF))))
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Mi perfil", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Purple40)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(Modifier.fillMaxWidth().padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(user?.name ?: "Entrenador Pokémon", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                user?.email?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(it, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().weight(1f, fill = false),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Tu compañero", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                if (companion.num >= 0) {
                    Image(
                        painter = painterResource(companion.image),
                        contentDescription = companion.name,
                        modifier = Modifier.size(190.dp)
                    )
                    Text(companion.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Purple40)
                    Text(
                        text = daysTogether?.let { "⭐ Lleva contigo $it ${if (it == 1L) "día" else "días"}" }
                            ?: "⭐ Es tu compañero",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF5E35B1)
                    )
                } else {
                    Box(Modifier.size(190.dp), contentAlignment = Alignment.Center) {
                        Text("Aún no has elegido un compañero", color = Color.Gray)
                    }
                }
            }
        }

        Button(onClick = onBack) { Text("Volver") }
    }
}
