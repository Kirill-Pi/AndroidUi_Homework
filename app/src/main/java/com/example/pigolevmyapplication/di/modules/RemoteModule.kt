package com.example.pigolevmyapplication.di.modules

import com.example.pigolevmyapplication.BuildConfig
import com.example.pigolevmyapplication.data.ApiConstants
import com.example.pigolevmyapplication.data.TmdbApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun bindRemoteModule(injectObject: InjectRemoteModule):InjectRemoteModuleInterface



}

class InjectRemoteModule @Inject constructor(): InjectRemoteModuleInterface {

}


interface InjectRemoteModuleInterface{
    abstract fun bindOkHttpClient()
    abstract fun bindRetrofit()
    abstract fun bindTmdbApi()


}


/*class RemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        //Добавляем логгер
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(ApiConstants.BASE_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideTmdbApi(retrofit: Retrofit): TmdbApi = retrofit.create(TmdbApi::class.java)
}*/