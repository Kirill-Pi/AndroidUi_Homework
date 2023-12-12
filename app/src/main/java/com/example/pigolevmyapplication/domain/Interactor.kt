package com.example.pigolevmyapplication.domain

import com.example.pigolevmyapplication.API
import com.example.pigolevmyapplication.data.MainRepository
import com.example.pigolevmyapplication.data.PreferenceProvider

import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.di.modules.TmdbApi
import com.example.pigolevmyapplication.utils.Converter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {


    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)
        //Метод getDefaultCategoryFromPreferences() будет получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiListToDTOList(it.tmdbFilms)

            }
            .subscribeBy(
                onError = {
                    progressBarState.onNext(false)
                },
                onNext = {
                    progressBarState.onNext(false)
                    repo.putToDb(it)
                }
            )
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