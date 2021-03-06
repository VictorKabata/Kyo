package com.vickikbt.kyoskinterview.ui.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.databinding.ItemViewpagerMoviesBinding
import timber.log.Timber

class MovieShowViewPagerAdapter constructor(
    private val inTheaterMovies: List<MovieShow>,
    private val onClick: (MovieShow) -> Unit
) : RecyclerView.Adapter<MovieShowViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewpagerMoviesBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inTheatersMovie = inTheaterMovies[position]
        val context = holder.itemView.context

        holder.bind(context, inTheatersMovie)

        holder.itemView.setOnClickListener {
            onClick(inTheatersMovie)
        }
    }

    override fun getItemCount() = inTheaterMovies.size

    inner class ViewHolder(private val binding: ItemViewpagerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, inTheatersMovie: MovieShow) {
            Glide.with(context)
                .load(inTheatersMovie.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Timber.e("Failed to get image bitmap")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        val imageBitmapDrawable = resource as BitmapDrawable
                        val imageBitmap = imageBitmapDrawable.bitmap

                        Palette.from(imageBitmap).maximumColorCount(20).generate { palette ->
                            val vibrantSwatch = palette?.vibrantSwatch
                            val dominantSwatch = palette?.dominantSwatch!!

                            if (vibrantSwatch != null) {
                                binding.fel.setBackgroundColor(vibrantSwatch.rgb)
                                binding.textViewName.setTextColor(vibrantSwatch.titleTextColor)
                            } else {
                                binding.fel.setBackgroundColor(dominantSwatch.rgb)
                                binding.textViewName.setTextColor(dominantSwatch.titleTextColor)
                            }

                        }

                        return false
                    }

                }).into(binding.imageViewCover)

            binding.textViewName.text = inTheatersMovie.title
        }
    }

}