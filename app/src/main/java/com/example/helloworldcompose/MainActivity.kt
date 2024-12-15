package com.example.helloworldcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var name by remember { mutableStateOf("") }
                var showGreeting by remember { mutableStateOf(true) }
                var isDarkTheme by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Enter your name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )

                        Button(
                            onClick = { showGreeting = !showGreeting },
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text(if (showGreeting) "Hide Greeting" else "Show Greeting")
                        }

                        AnimatedVisibility(
                            visible = showGreeting,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "Hello, ${if (name.isBlank()) "World" else name}!",
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }

                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { isDarkTheme = it },
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = if (isDarkTheme) "Dark Theme" else "Light Theme",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}