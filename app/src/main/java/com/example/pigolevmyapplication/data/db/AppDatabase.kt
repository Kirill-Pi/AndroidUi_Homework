package com.example.pigolevmyapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pigolevmyapplication.data.dao.FilmDao
import com.example.pigolevmyapplication.data.entity.Film

@Database(entities = [Film::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}