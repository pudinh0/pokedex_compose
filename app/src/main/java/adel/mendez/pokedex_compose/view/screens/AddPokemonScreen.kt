package adel.mendez.pokedex_compose.view.screens

import adel.mendez.pokedex_compose.model.domain.pokemonTypes
import adel.mendez.pokedex_compose.viewmodel.AddPokemonViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(innerPadding: PaddingValues, viewModel: AddPokemonViewModel) {
    val number = remember { TextFieldState() }
    val name = remember { TextFieldState() }
    val desc = remember { TextFieldState() }
    var openType by remember { mutableStateOf(false) }
    val type = remember { TextFieldState(pokemonTypes[0]) }
    var inputError by remember { mutableStateOf<String?>(null) }
    val pokemonList by viewModel.pokemons.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        //falta agregar los demas datos de lpokemon
        //
        Text("Agregar Pokemon")
        Spacer(Modifier.size(20.dp))
        TextField(state = number,
            label = {Text("Numero de pokemon:")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        TextField(state = name,
            label = {Text("Nombre:")})

        ExposedDropdownMenuBox(
            expanded = openType,
            onExpandedChange = {openType = it}
        ) {
            OutlinedTextField(
                readOnly = true,
                label = {Text("Selecciona un tipo")},
                state = type,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType)
                },
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(
                expanded = openType,
                onDismissRequest = {openType = false}
            ) {

                pokemonTypes.forEach {
                    t->
                    DropdownMenuItem(text = {Text(t)},
                            onClick = {type.setTextAndPlaceCursorAtEnd(t)
                            openType=false}
                    )
                }
            }
        }

        TextField(state = desc,
            label = {Text("Descripción:")})

        Spacer(Modifier.size(15.dp))

        Button(onClick = {
            val pokemonNumber = number.text.toString().toIntOrNull()
            if (pokemonNumber == null) {
                inputError = "Escribe un número de Pokémon válido."
            } else {
                inputError = null
                viewModel.savePokemon(
                    name = name.text.toString(),
                    type = type.text.toString(),
                    number = pokemonNumber,
                    description = desc.text.toString()
                )
            }
        }) {
            Text("Agregar pokemon")
        }

        inputError?.let { Text(it, color = Color.Red) }
        viewModel.errorMessage?.let { Text(it, color = Color.Red) }
        if (viewModel.pokemonSaved) Text("Pokémon guardado.")

        Text("Pokémon guardados")
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList, key = { it.num }) { pokemon ->
                Text(pokemon.toString())
            }
        }
    }
}
