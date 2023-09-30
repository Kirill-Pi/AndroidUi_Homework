package com.example.pigolevmyapplication.domain

import com.example.pigolevmyapplication.API
import com.example.pigolevmyapplication.data.MainRepository
import com.example.pigolevmyapplication.data.PreferenceProvider
import com.example.pigolevmyapplication.data.TmdbApi
import com.example.pigolevmyapplication.data.TmdbResultsDto
import com.example.pigolevmyapplication.data.entity.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)
    fun getFilmsFromApi(page: Int) {

        scope.launch {
            progressBarState.send(true)
        }


        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
               // заполняем список, используя оператер map
                val list = mutableListOf<Film>()
                val result = response.body()?.tmdbFilms?.map {
                    list.add(
                        Film(
                            title = it.title,
                            poster = it.posterPath,
                            description = it.overview,
                            rating = it.voteAverage,
                            isInFavorites = false
                        )
                    )
                }
                //Кладем фильмы в бд
               scope.launch {
                   repo.putToDb(list)
                   progressBarState.send(false)
               }
            }
            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала обнуляем progress bar
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Flow<MutableList<Film>> = repo.getAllFromDB()

    //fun getFilmsByNameFromDB(name : String): MutableList<Film> = repo.getByNameFromDB()
}