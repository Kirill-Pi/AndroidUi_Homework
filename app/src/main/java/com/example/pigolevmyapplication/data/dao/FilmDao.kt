package com.example.pigolevmyapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pigolevmyapplication.data.entity.Film
import kotlinx.coroutines.flow.Flow

//Помечаем, что это не просто интерфейс, а Dao-объект
@Dao
interface FilmDao {
    //Запрос на всю таблицу
    @Query("SELECT * FROM cached_films")
    //fun getCachedFilms(): LiveData<MutableList<Film>>
    fun getCachedFilms(): Flow<MutableList<Film>>

    //Кладём списком в БД, в случае конфликта перезаписываем
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Film>)
}