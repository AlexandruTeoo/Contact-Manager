package com.example.contact_manager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact.db")
data class Contact(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val email: String
)
