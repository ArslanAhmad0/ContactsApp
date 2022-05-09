package com.example.contactsapp.network

import com.example.contactsapp.model.RoomList
import com.example.contactsapp.model.StaffList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("people")
    suspend fun getStaff():Response<List<StaffList>>
    @GET("rooms")
    suspend fun getRooms():Response<List<RoomList>>
}