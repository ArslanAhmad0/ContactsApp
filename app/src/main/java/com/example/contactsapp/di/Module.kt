package com.example.contactsapp.di

import com.example.contactsapp.network.ApiService
import com.example.contactsapp.network.StaffRepository
import com.example.contactsapp.room.StaffDataBase
import com.example.contactsapp.viewmodel.StaffViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module{

    viewModel {

        StaffViewModel(get())
    }
}

val repositoryModule = module {

    single {
        val database = StaffDataBase.getDatabase(androidContext())
        StaffRepository(get(),database)
    }
}

val apiModule = module {

    fun provideApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    single { provideApi(get()) }
}

val retrofitModule = module {
    fun provideRetrofit() = Retrofit.Builder()
                            .baseUrl("https://61e947967bc0550017bc61bf.mockapi.io/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

    single { provideRetrofit() }
}
