package com.example.pigolevmyapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pigolevmyapplication.data.entity.Film

class FavoritesFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<MutableList<Film>> = MutableLiveData()
    //Инициализируем интерактор
   // var interactor: Interactor = App.instance.interactor
 /*   init {
        val favorites = interactor.getFavoritesDB ()
        filmsListLiveData.postValue(favorites)
    }*/


}