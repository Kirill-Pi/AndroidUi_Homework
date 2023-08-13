package com.example.pigolevmyapplication.utils

import com.example.pigolevmyapplication.data.TmdbFilm
import com.example.pigolevmyapplication.domain.Film

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): MutableList<Film> {
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
        return result
    }
}