package com.example.contact_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contact_manager.ui.theme.Contact_ManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Contact_ManagerTheme {
                val database = DatabaseProvider.getDatabase(this)
                val contactViewModel = viewModel<ContactViewModel>(factory = ContactViewModelFactory(database))
                SetupNavGraph(contactViewModel)
            }
        }
    }
}

@Composable
fun SetupNavGraph(contactViewModel: ContactViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen"){
         HomeScreen(navController = navController, viewModel = contactViewModel)
        }
        composable("add_contact"){
         AddContactScreen(navController = navController, viewModel = contactViewModel)
        }
    }
}