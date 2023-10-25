package com.example.pigolevmyapplication.domain

import com.example.pigolevmyapplication.API
import com.example.pigolevmyapplication.data.MainRepository
import com.example.pigolevmyapplication.data.PreferenceProvider
import com.example.pigolevmyapplication.data.TmdbApi
import com.example.pigolevmyapplication.data.TmdbResultsDto
import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.utils.Converter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {


    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getFilmsFromApi(page: Int) {
        //Отправляем progressBar
            progressBarState.onNext(true)

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
                Completable.fromSingle<List<Film>> {
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала обнуляем progress bar
                    progressBarState.onNext(false)
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Observable<MutableList<Film>> = repo.getAllFromDB()


    fun getSearchResultFromApi(search: String, page : Int): Observable<MutableList<Film>> {
        val result = retrofitService.getFilmFromSearch(API.KEY, "ru-RU", search, page)
            .map {
                Converter.convertApiListToDTOList(it.tmdbFilms)
            }
        return result
    }


    //fun getFilmsByNameFromDB(name : String): MutableList<Film> = repo.getByNameFromDB()
}