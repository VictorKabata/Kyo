package com.vickikbt.kyoskinterview.ui.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.domain.models.Top250MovieShow
import com.vickikbt.kyoskinterview.databinding.ItemRvMovieBinding


class Top250MoviesAdapter constructor(private val top250Movies: List<Top250MovieShow>) :
    RecyclerView.Adapter<Top250MoviesAdapter.Top250MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top250MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvMovieBinding.inflate(layoutInflater, parent, false)

        return Top250MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Top250MovieViewHolder, position: Int) {
        val top250Movie = top250Movies[position]
        val context = holder.itemView.context

        holder.bind(context, top250Movie)
    }

    override fun getItemCount() = top250Movies.size

    inner class Top250MovieViewHolder(private val binding: ItemRvMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, top250Movie: Top250MovieShow) {
            Glide.with(context)
                .load(top250Movie.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(binding.imageViewImage)

            binding.textViewName.text = top250Movie.title

            val rating = top250Movie.imDbRating

            if (rating != "0.0" && !rating.isNullOrEmpty()) {
                val firstPart = rating.substringAfter(".")
                val lastPart = rating.substring(rating.length - 1)
                val subString = Html.fromHtml("${firstPart}.<sup>$lastPart</sup>")

                binding.textViewRating.text = subString
            } else {
                binding.frameLayoutRating.visibility = View.GONE
            }
        }
    }
}