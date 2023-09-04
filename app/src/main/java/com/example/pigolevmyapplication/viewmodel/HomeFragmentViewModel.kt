package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.App
import com.example.pigolevmyapplication.domain.Film
import com.example.pigolevmyapplication.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<MutableList<Film>> = MutableLiveData()
    //Инициализируем интерактор
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    var currentPage = 1
    var isLoaded = false
    lateinit var tempFilmData :  MutableList<Film>
    init {
        App.instance.dagger.inject(this)
        getFilms()
    }

  fun nextPaqe() {
     //Загружаем следующую страницу
        currentPage++
        getFilms()
        isLoaded = true
    }

    fun previousPage(){
    //Загружаем предыдущую страницу
        if (currentPage > 1){
            currentPage --
            getFilms()
        }
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: MutableList<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
                filmsListLiveData.postValue(interactor.getFilmsFromDB())
            }
        })
    }


   /* fun interactorStart(){
        interactor.getFilmsFromApi(currentPage, object : ApiCallback {
            override fun onSuccess(films: MutableList<Film>) {
                //tempFilmData = films
               films.forEach { println(it.title) }
                filmsListLiveData.postValue(films)
            }
            override fun onFailure() {
            }
        })
    }*/

    interface ApiCallback {
        fun onSuccess(films: MutableList<Film>)
        fun onFailure()
    }
}