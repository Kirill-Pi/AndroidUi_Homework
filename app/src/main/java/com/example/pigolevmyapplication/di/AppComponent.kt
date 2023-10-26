package com.example.pigolevmyapplication.di

import com.example.pigolevmyapplication.di.modules.DatabaseModule
import com.example.pigolevmyapplication.di.modules.DomainModule
import com.example.pigolevmyapplication.di.modules.RemoteProvider

import com.example.pigolevmyapplication.viewmodel.HomeFragmentViewModel
import com.example.pigolevmyapplication.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    dependencies = [RemoteProvider::class],
    modules = [
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}