package com.example.contact_manager

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactViewModel (private val database: ContactDB): ViewModel() {
    val allEntries = database.contactDAO().getAllEntries()

    fun upsertEntry (name: String, phoneNumber: String, email: String){
        viewModelScope.launch {
            val entry = Contact(name = name, phoneNumber = phoneNumber, email = email)
            database.contactDAO().upsertContact(entry)
        }
    }

    fun deleteEntry (contact: Contact){
        viewModelScope.launch {
            try{
                database.contactDAO().deleteContact(contact)
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}