package com.example.pigolevmyapplication


import android.app.Application
import com.example.pigolevmyapplication.di.AppComponent
import com.example.pigolevmyapplication.di.DaggerAppComponent
import com.example.pigolevmyapplication.di.modules.DaggerRemoteComponent
import com.example.pigolevmyapplication.di.modules.DatabaseModule
import com.example.pigolevmyapplication.di.modules.DomainModule


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        val remoteProvider = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteProvider)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}