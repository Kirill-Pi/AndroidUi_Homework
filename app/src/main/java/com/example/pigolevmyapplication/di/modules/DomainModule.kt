package com.example.pigolevmyapplication.di.modules

import com.example.pigolevmyapplication.data.MainRepository
import com.example.pigolevmyapplication.data.TmdbApi
import com.example.pigolevmyapplication.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}