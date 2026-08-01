package com.miuix.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var cookieValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "登录"
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(32.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = cookieValue,
                    onValueChange = { cookieValue = it },
                    label = "cookie"
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (cookieValue.isNotBlank()) {
                            onLoginSuccess()
                        }
                    },
                    enabled = cookieValue.isNotBlank()
                ) {
                    Text("登录")
                }
            }
        }
    }
}