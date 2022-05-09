package com.example.contactsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.model.StaffList

@Database(entities = [StaffList::class], version = 1)
abstract class StaffDataBase : RoomDatabase(){


    abstract fun staffDao() : RoomDao
    companion object{
        private var INSTANCE :StaffDataBase? = null
        fun getDatabase(context: Context) : StaffDataBase{
            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context,StaffDataBase::class.java,"staffDB"
                ).build()
            }
            return INSTANCE!!
        }
    }
}