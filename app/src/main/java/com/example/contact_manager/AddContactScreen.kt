package com.example.contact_manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddContactScreen (navController: NavController, viewModel: ContactViewModel = viewModel()){
    var name by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Scaffold(
        content = {padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = name,
                    onValueChange = {name = it},
                    label = { Text(text = "Name")}
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = phoneNumber,
                    onValueChange = {phoneNumber = it},
                    label = { Text(text = "Phone number") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = email,
                    onValueChange = {email = it},
                    label = { Text(text = "Email") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    viewModel.upsertEntry(name, phoneNumber, email)
                    navController.navigateUp()
                }) {
                    Text(text = "Add")
                }
            }
        }
    )
}