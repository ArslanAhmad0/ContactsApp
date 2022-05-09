package com.example.contactsapp.network

import com.example.contactsapp.model.RoomList
import com.example.contactsapp.model.StaffList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class StaffRepositoryTest  {

    private val service : ApiService = mockk()
    private lateinit var repository : StaffRepository
    @Before
    fun setup(){
        repository = StaffRepository(service)
    }
    @Test
    fun `when get staff is called, then return list of staff`() = runBlocking{

        coEvery { service.getStaff() } returns Response.success(
            listOf(StaffList("Arslan",
                "https//",
                "Ahmad",
                "Arslan@G.com",
                "Developer",
                "Red",
                8,
                "23/04/2022"))
        )
        val staffResponse = repository.getStaffFormNetwork()

        assert(staffResponse.body()!!.first().firstName == "Arslan")
        assert(staffResponse.body()!!.first().avatar == "https//")
        assert(staffResponse.body()!!.first().lastName == "Ahmad")
        assert(staffResponse.body()!!.first().email == "Arslan@G.com")
        assert(staffResponse.body()!!.first().jobtitle == "Developer")
        assert(staffResponse.body()!!.first().favouriteColor == "Red")
        assert(staffResponse.body()!!.first().id == 8)
        assert(staffResponse.body()!!.first().createdAt == "23/04/2022")
    }
    @Test
    fun `when get room is called, then return list of staff`() = runBlocking{

        coEvery { service.getRooms() } returns Response.success(
            listOf(
                RoomList(true,3)
            )
        )
        val roomResponse = repository.getRooms()

        assert(roomResponse.body()!!.first().isOccupied)
        assert(roomResponse.body()!!.first().id == 3)

    }

}