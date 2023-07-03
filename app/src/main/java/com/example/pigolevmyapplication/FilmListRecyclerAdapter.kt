package com.example.pigolevmyapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<Film>()
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
                val itemContainer = holder.itemView.findViewById<ViewGroup>(R.id.item_container)
                itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }


    fun updateItems(list: MutableList<Film>) {
        val oldList = this.items
        val diff = FilmDiff(oldList, list)
        val diffResult = DiffUtil.calculateDiff(diff)
        this.items = list
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItems (newList: MutableList<Film>) {
        var addList = this.items
        addList.addAll(newList)
        updateItems(addList)
    }




    interface OnItemClickListener {
        fun click(film: Film)
    }
}