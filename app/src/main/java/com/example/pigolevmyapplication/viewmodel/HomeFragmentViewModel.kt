package com.example.pigolevmyapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.App
import com.example.pigolevmyapplication.domain.Film
import com.example.pigolevmyapplication.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()
    //Инициализируем интерактор
    var interactor: Interactor = App.instance.interactor


    init {
        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)

    }
}