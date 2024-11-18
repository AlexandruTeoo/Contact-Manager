package com.example.contact_manager

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseProvider {
    @Volatile
    private var INSTANCE: ContactDB? = null

    fun getDatabase(context: Context): ContactDB{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ContactDB::class.java,
                "contact.db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}