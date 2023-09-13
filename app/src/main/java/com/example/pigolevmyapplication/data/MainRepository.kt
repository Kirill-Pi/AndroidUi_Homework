package com.example.pigolevmyapplication.data

import androidx.lifecycle.LiveData
import com.example.pigolevmyapplication.data.dao.FilmDao
import com.example.pigolevmyapplication.data.entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<MutableList<Film>> = filmDao.getCachedFilms()
}