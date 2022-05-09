package com.example.contactsapp.model

data class LoadingState constructor(
    val status: Status,
    val message: String?=null
){
    companion object{
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.LOADING)
        val ERROR = LoadingState(Status.FAILED)
    }
}

enum class Status{
    SUCCESS,
    LOADING,
    FAILED
}
