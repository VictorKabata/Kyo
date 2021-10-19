package com.vickikbt.kyoskinterview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.kyoskinterview.databinding.ItemRvMovieBinding

class PopularMoviesAdapter constructor(private val popularMovies: List<PopularMovieShow>) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvMovieBinding.inflate(layoutInflater, parent, false)

        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val popularMovie = popularMovies[position]
        val context = holder.itemView.context

        holder.bind(context, popularMovie)
    }

    override fun getItemCount() = popularMovies.size

    inner class PopularMovieViewHolder(private val binding: ItemRvMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, popularMovie: PopularMovieShow) {
            Glide.with(context)
                .load(popularMovie.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(binding.imageViewImage)

            binding.textViewName.text = popularMovie.title
        }
    }
}