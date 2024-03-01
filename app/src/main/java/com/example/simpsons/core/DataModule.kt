package com.example.simpsons.core

import com.example.simpsons.network.WebService
import com.example.simpsons.viewmodels.MainViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {


    val myViewModelModule : Module = module {
        single {  networkModule }
        viewModel{ MainViewModel( retrofitClient = get())}
    }

    val networkModule = module {
        single {
                Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build().create(WebService::class.java)
            }
        }

    val appModule = listOf(
        myViewModelModule,
        networkModule,
    )
    }
