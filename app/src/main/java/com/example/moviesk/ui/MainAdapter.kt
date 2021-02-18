package com.example.moviesk.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesk.base.BaseViewHolder
import com.example.moviesk.data.model.Movie
import com.example.moviesk.databinding.MoviesItemSpanBinding
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.movies_item_span.view.*

class MainAdapter(
    private val context: Context, private val moviesList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MoviesItemSpanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        //val view = itemBinding.root
        val holder = MainViewHolder(itemBinding)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(moviesList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    //To avoid memory leaks I use inner so it dies when MainAdapter dies

    inner class MainViewHolder(val binding: MoviesItemSpanBinding) :
        BaseViewHolder<Movie>(binding.root) {
        var movie_id: String = ""
        override fun bind(item: Movie, position: Int) {
            val baseurlimg = "https://image.tmdb.org/t/p"
            val sizeimg = "/w500/"
            //baseurlimg+sizeimg+item.imagen
            //Glide.with(context).load(item.imagen).into(itemView.iv_imagen)
            Glide.with(context).load(baseurlimg + sizeimg + item.imagen).into(itemView.iv_imagen)
            binding.tvNombre.text = item.nombre
            binding.tvFestreno.text = item.festreno
            binding.tvCalificacion.text = item.rating
            binding.root.setOnClickListener { itemClickListener.onMovieClick(item) }


        }
    }
}