package com.example.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.model.LoadingState
import com.example.contactsapp.model.RoomList
import com.example.contactsapp.model.StaffList
import com.example.contactsapp.network.StaffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StaffViewModel(private val repository: StaffRepository): ViewModel() {

    private val _staffList =MutableLiveData<List<StaffList>>()
    val staffList:LiveData<List<StaffList>>
    get() = _staffList

    private val _roomsList =MutableLiveData<List<RoomList>>()
    val roomList:LiveData<List<RoomList>>
        get() = _roomsList


    private val _loadingState= MutableLiveData<LoadingState>()
    val loadingState:LiveData<LoadingState>
    get() = _loadingState

    init {
        viewModelScope.launch {

           repository.getStaffFormNetwork()
        }
    }

    fun fetchStaff(){
        _loadingState.value=LoadingState.LOADING
        viewModelScope.launch(Dispatchers.IO) {

          val result =  repository.getStaffFromDB()

              //  if(result.isEmpty()) {
                    _staffList.postValue(result)
                    _loadingState.postValue(LoadingState.LOADED)
              //  }

        }
    }

    fun fetchRooms(){
        _loadingState.value=LoadingState.LOADING
        viewModelScope.launch (Dispatchers.IO){
            val roomsResponse= repository.getRooms()
            if (roomsResponse.isSuccessful) {
                _roomsList.postValue(roomsResponse.body())
                _loadingState.postValue(LoadingState.LOADED)
            } else {
                _loadingState.postValue(LoadingState.ERROR)
            }
        }
    }


}