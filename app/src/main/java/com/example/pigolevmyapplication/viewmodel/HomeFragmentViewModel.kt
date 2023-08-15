package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.pigolevmyapplication.domain.Film
import com.example.pigolevmyapplication.domain.Interactor

import org.koin.java.KoinJavaComponent.inject


class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<MutableList<Film>> = MutableLiveData()
    //Инициализируем интерактор
    private val interactor: Interactor by inject(Interactor::class.java)

    var currentPage = 1
    var isLoaded = false
    lateinit var tempFilmData :  MutableList<Film>
    init {
        interactorStart()
    }

    fun nextPaqe() {
     //Загружаем следующую страницу
        currentPage++
        interactorStart()
        isLoaded = true
    }

    fun previousPage(){
    //Загружаем предыдущую страницу
        if (currentPage > 1){
            currentPage --
            interactorStart()
        }
    }


    private fun interactorStart(){
        interactor.getFilmsFromApi(currentPage, object : ApiCallback {
            override fun onSuccess(films: MutableList<Film>) {
                tempFilmData = films
                tempFilmData.forEach { println(it.title) }
                filmsListLiveData.postValue(films)
            }
            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: MutableList<Film>)
        fun onFailure()
    }
}