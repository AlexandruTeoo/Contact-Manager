package com.example.contact_manager

import android.graphics.Paint.Style
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(navController: NavController, viewModel: ContactViewModel = viewModel()){
    val contacts = viewModel.allEntries.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_contact")
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        },
        content = {padding ->
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                Column {
                    Text(text = "Phone Contacts", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(contacts.value) {contact ->
                            ContactItem(contact, viewModel)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ContactItem(contact: Contact, viewModel: ContactViewModel){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(text = contact.name)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = contact.phoneNumber)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = contact.email)
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(onClick = {
            viewModel.deleteEntry(contact)
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Contact"
            )
        }
    }
}