package com.example.pigolevmyapplication

import android.app.Application
import com.example.pigolevmyapplication.data.ApiConstants
import com.example.pigolevmyapplication.data.MainRepository
import com.example.pigolevmyapplication.data.TmdbApi
import com.example.pigolevmyapplication.di.AppComponent
import com.example.pigolevmyapplication.di.DaggerAppComponent
import com.example.pigolevmyapplication.di.modules.DatabaseModule
import com.example.pigolevmyapplication.di.modules.DomainModule
import com.example.pigolevmyapplication.di.modules.RemoteModule


import com.example.pigolevmyapplication.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}