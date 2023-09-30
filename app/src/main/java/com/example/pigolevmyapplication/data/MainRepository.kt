package com.example.pigolevmyapplication.data

import com.example.pigolevmyapplication.data.dao.FilmDao
import com.example.pigolevmyapplication.data.entity.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Создаем CoroutineScope для звпросов в БД

        CoroutineScope(EmptyCoroutineContext).launch {
            filmDao.insertAll(films)
        }


    }

    fun getAllFromDB(): Flow<MutableList<Film>> = filmDao.getCachedFilms()
}