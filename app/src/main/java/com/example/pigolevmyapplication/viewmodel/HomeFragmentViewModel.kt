package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.App
import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<MutableList<Film>>
    var currentPage = 1
    var isLoaded = false
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var tempFilmData :  MutableList<Film>
    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
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
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(currentPage, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
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
        fun onSuccess()
        fun onFailure()
    }
}