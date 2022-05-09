package com.example.contactsapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contactsapp.model.StaffList


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaff(staff:List<StaffList>)
    @Query("SELECT * FROM StaffListTable")
    suspend fun getStaff() : List<StaffList>
}