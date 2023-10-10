package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.App
import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.domain.Interactor
import com.example.pigolevmyapplication.utils.SingleLiveEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор

    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Observable<MutableList<Film>>
    val showProgressBar : BehaviorSubject<Boolean>
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


}

