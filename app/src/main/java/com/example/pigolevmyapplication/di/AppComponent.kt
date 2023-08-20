package com.example.pigolevmyapplication.di

import com.example.pigolevmyapplication.di.modules.DatabaseModule
import com.example.pigolevmyapplication.di.modules.DomainModule
import com.example.pigolevmyapplication.di.modules.RemoteModule
import com.example.pigolevmyapplication.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}