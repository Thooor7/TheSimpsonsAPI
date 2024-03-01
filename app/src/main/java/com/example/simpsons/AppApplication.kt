package com.example.simpsons

import android.app.Application
import com.example.simpsons.core.DataModule
import com.example.simpsons.core.DataModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(
                appModule
            )
        }
//        DataModule.load()

    }
}