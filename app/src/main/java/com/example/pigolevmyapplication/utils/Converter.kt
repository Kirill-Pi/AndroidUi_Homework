package com.example.pigolevmyapplication.utils

import com.example.pigolevmyapplication.data.TmdbFilm
import com.example.pigolevmyapplication.data.entity.Film

object Converter {
    fun convertApiListToDTOList(list: List<TmdbFilm>?): MutableList<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            ))
        }
        result.forEach {
            println(it.title)
        }
        return result
    }
}