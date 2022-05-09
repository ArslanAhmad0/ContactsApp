package com.example.contactsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.contactsapp.model.RoomList
import com.example.contactsapp.model.StaffList
import com.example.contactsapp.network.StaffRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class StaffViewModelTest  {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val repository : StaffRepository = mockk()
    private lateinit var viewModel: StaffViewModel
    private val staffObserver :Observer<List<StaffList>> = mockk(relaxed = true)
    private val roomObserver : Observer<List<RoomList>> = mockk(relaxed = true)

    @Before
    fun setup(){
        viewModel = StaffViewModel(repository)

        viewModel.staffList.observeForever(staffObserver)

        viewModel.roomList.observeForever(roomObserver)
    }
   @After
   fun tearDown(){
       viewModel.staffList.removeObserver(staffObserver)
       viewModel.roomList.removeObserver(roomObserver)
   }
    @Test
    fun `when getStaff is called then return staff from Api`() = runBlocking{

        coEvery {
            repository.getStaffFormNetwork()
        } returns  Response.success(
                listOf(StaffList("Arslan",
                    "https//",
                    "Ahmad",
                    "Arslan@G.com",
                    "Developer",
                    "Red",
                    8,
                    "23/04/2022"))
                )

        viewModel.fetchStaff()

        verify{
            staffObserver.onChanged(listOf(StaffList("Arslan",
                "https//",
                "Ahmad",
                "Arslan@G.com",
                "Developer",
                "Red",
                8,
                "23/04/2022")))
        }

    }

    @Test
    fun `when getRoom is called then return Rooms from Api`() = runBlocking{

        coEvery {
            repository.getRooms()
        } returns  Response.success(
            listOf(RoomList(true,1))
        )

        viewModel.fetchRooms()

        verify{
            roomObserver.onChanged(listOf(RoomList(true,1)))
        }

    }




}