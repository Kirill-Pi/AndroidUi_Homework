package com.example.pigolevmyapplication.di.modules

import com.example.pigolevmyapplication.data.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton



    @Module
    abstract class DatabaseModule {

        @Binds
        @Singleton
        abstract fun bindRepository(MainRepository: MainRepository): InjectRepositoryInterface
    }

        interface InjectRepositoryInterface


   /* class DatabaseModule {
        @Provides
        @Singleton
        fun provideRepository() = MainRepository()
    }*/