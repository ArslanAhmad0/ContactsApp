package com.example.contactsapp.network

import com.example.contactsapp.room.StaffDataBase


class StaffRepository(
    private val service: ApiService,
    private val staffDataBase: StaffDataBase) {

    suspend fun getStaffFormNetwork() {
        val result = service.getStaff()
        staffDataBase.staffDao().insertStaff(result.body()!!)
    }

    suspend fun getStaffFromDB() = staffDataBase.staffDao().getStaff()

    suspend fun getRooms()= service.getRooms()
}