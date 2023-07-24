package com.example.pigolevmyapplication.domain

import com.example.pigolevmyapplication.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase

    fun getFavoritesDB () : MutableList<Film> {
        var temp = mutableListOf<Film>()
        temp.clear()
        repo.filmsDataBase.forEach {
            if (it.isInFavorites) temp.add(it)
        }
        return temp
    }

}