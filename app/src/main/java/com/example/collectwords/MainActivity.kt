package com.example.collectwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.collectwords.ui.theme.CollectWordsTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollectWordsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollectWords()
                }
            }
        }
    }
}

@Composable
fun CollectWords() {
    // https://tigeroakes.com/posts/mutablestateof-list-vs-mutablestatelistof/
    val words = remember { mutableStateListOf<String>() }
    var word by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(true) }
    val delete: (String) -> Unit = { words.remove(it) }

    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Collect words", style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter a word") }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                words.add(word)
            }) {
                Text("Add")
            }
            Button(onClick = {
                words.clear()
                word = ""
                result = ""
            }) {
                Text("Clear")
            }
            Button(onClick = { result = words.joinToString() }) {
                Text("Show")
            }
        }
        if (result.isNotEmpty()) {
            Text(result)
        } else {
            Text("Empty", fontStyle = FontStyle.Italic)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show list")
            Spacer(modifier = Modifier.padding(5.dp))
            Switch(checked = showList, onCheckedChange = { showList = it })
        }
        if (showList) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(words) { wo ->
                    Text(wo, modifier = Modifier.clickable { delete(wo) })
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CollectWordsPreview() {
    // CollectWordsTheme {
    CollectWords()
    // }
}