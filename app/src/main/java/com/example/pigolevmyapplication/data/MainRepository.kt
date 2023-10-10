package com.example.pigolevmyapplication.data


import com.example.pigolevmyapplication.data.dao.FilmDao
import com.example.pigolevmyapplication.data.entity.Film
import io.reactivex.rxjava3.core.Observable

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: MutableList<Film>) {
            filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<MutableList<Film>> = filmDao.getCachedFilms()
}