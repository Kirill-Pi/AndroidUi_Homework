package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.App
import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.domain.Interactor
import com.example.pigolevmyapplication.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор

    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Flow<MutableList<Film>>
    val showProgressBar : Channel <Boolean>
    var currentPage = 1
    var isLoaded = false

    val errorEvent = SingleLiveEvent <String>()
    lateinit var tempFilmData :  MutableList<Film>
    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
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

        interactor.getFilmsFromApi(currentPage)
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

