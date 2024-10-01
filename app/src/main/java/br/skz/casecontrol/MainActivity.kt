package br.skz.casecontrol

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import br.skz.casecontrol.classes.ProcessEntity
import br.skz.casecontrol.ui.theme.CaseControlTheme
import br.skz.casecontrol.viewModels.ProcessViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaseControlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: ProcessViewModel = viewModel(), name:String, modifier: Modifier) {

    var processNumber by remember { mutableStateOf("") }
    var party by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("In Progress") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Processos Jurídicos", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        // Input fields for adding a process
        TextField(
            value = processNumber,
            onValueChange = { processNumber = it },
            label = { Text("Número do Processo") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = party,
            onValueChange = { party = it },
            label = { Text("Nome da Parte") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição do Processo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addProcess(ProcessEntity(number = processNumber, party = party, description = description, status = status))
                processNumber = ""
                party = ""
                description = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Adicionar Processo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of processes
        Text(text = "Lista de Processos", style = MaterialTheme.typography.h6)

        for (process in viewModel.processes) {
            ProcessItem(process = process, onDelete = { viewModel.deleteProcess(process.id) })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Consult important dates for a process
        var searchNumber by remember { mutableStateOf("") }
        TextField(
            value = searchNumber,
            onValueChange = { searchNumber = it },
            label = { Text("Consultar Datas Importantes") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { /* Consult API DataJud */ }) {
            Text("Consultar")
        }
    }
}

@Composable
fun ProcessItem(process: ProcessEntity, onDelete: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Número: ${process.number}")
            Text(text = "Parte: ${process.party}")
            Text(text = "Descrição: ${process.description}")
            Text(text = "Status: ${process.status}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onDelete) {
                Text("Excluir")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(name = "preview", modifier = Modifier )
}
