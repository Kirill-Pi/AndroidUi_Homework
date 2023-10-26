package com.example.pigolevmyapplication.di.modules

interface RemoteProvider {
    fun provideRemote(): TmdbApi
}