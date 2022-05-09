package com.example.contactsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StaffListTable")
data class StaffList(
    val firstName:String,
    val avatar:String,
    val lastName:String,
    val email:String,
    val jobtitle:String,
    val favouriteColor:String,
    @PrimaryKey
    val id:Int,
    val createdAt:String
) : java.io.Serializable
