package com.example.pigolevmyapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val ratingDonut = itemView.findViewById<RatingDonutView>(R.id.rating_donut)

    fun bind(film: Film) {
        title.text = film.title

        //Using Glide to add pictures

        Glide.with(itemView)
            .load(film.poster)
            .centerCrop()
            .into(poster)
        this.description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}