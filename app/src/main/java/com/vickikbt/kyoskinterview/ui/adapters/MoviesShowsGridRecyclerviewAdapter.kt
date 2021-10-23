package com.vickikbt.kyoskinterview.ui.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.databinding.ItemRvMovieBinding
import com.vickikbt.kyoskinterview.databinding.ItemRvMovieGridBinding

class MoviesShowsGridRecyclerviewAdapter constructor(
    private val movies: List<MovieShow>,
    private val onClick: (MovieShow) -> Unit
) : RecyclerView.Adapter<MoviesShowsGridRecyclerviewAdapter.MoviesShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesShowsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvMovieGridBinding.inflate(layoutInflater, parent, false)

        return MoviesShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesShowsViewHolder, position: Int) {
        val movieShow = movies[position]
        val context = holder.itemView.context

        holder.bind(context, movieShow)

        holder.itemView.setOnClickListener {
            onClick(movieShow)
        }
    }

    override fun getItemCount() = movies.size

    inner class MoviesShowsViewHolder(private val binding: ItemRvMovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, movie: MovieShow) {
            Glide.with(context)
                .load(movie.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(binding.imageViewImage)

            binding.textViewName.text = movie.title

            val rating = movie.imDbRating

            if (rating != "0.0" && !rating.isNullOrEmpty()) {
                binding.textViewRating.text = rating
            } else {
                binding.frameLayoutRating.visibility = View.GONE
            }
        }
    }
}