package com.vickikbt.kyoskinterview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.domain.models.Actor
import com.vickikbt.kyoskinterview.databinding.ItemRvCastBinding
import timber.log.Timber

class CastsAdapter constructor(
    private val actors: List<Actor>
) : RecyclerView.Adapter<CastsAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCastBinding.inflate(layoutInflater, parent, false)

        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val actor = actors[position]
        val context = holder.itemView.context

        holder.bind(context, actor)
    }

    override fun getItemCount() = actors.size

    inner class CastViewHolder(private val binding: ItemRvCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, actor: Actor) {
            Glide.with(context)
                .load(actor.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(binding.imageViewActor)

            binding.textViewName.text = actor.name
        }
    }
}