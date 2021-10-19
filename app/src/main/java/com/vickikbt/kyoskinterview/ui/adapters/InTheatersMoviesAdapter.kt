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
import com.vickikbt.domain.models.InTheatersComingSoonMovie
import com.vickikbt.kyoskinterview.databinding.ItemHeaderViewpagerBinding
import timber.log.Timber

class InTheatersMoviesAdapter constructor(
    private val inTheaterMovies: List<InTheatersComingSoonMovie>
) : RecyclerView.Adapter<InTheatersMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHeaderViewpagerBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inTheatersMovie = inTheaterMovies[position]
        val context = holder.itemView.context

        holder.bind(context, inTheatersMovie)
    }

    override fun getItemCount() = 5

    inner class ViewHolder(private val binding: ItemHeaderViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, inTheatersMovie: InTheatersComingSoonMovie) {
            Glide.with(context)
                .load(inTheatersMovie.image)
                //.placeholder(R.drawable.image_placeholder)
                //.error(R.drawable.image_placeholder)
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