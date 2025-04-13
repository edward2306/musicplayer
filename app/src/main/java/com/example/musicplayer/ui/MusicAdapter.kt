package com.example.musicplayer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.R
import com.example.domain.model.MusicItem

class MusicAdapter(private val onItemClick: (MusicItem) -> Unit, private val currentlyPlayingId: LiveData<Long?>) : ListAdapter<MusicItem, MusicAdapter.MusicViewHolder>(DIFF_CALLBACK) {
    private var selectedIndex = -1
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicItem>() {
            override fun areItemsTheSame(oldItem: MusicItem, newItem: MusicItem) =
                oldItem.trackId == newItem.trackId

            override fun areContentsTheSame(oldItem: MusicItem, newItem: MusicItem) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position), position == selectedIndex)
    }

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imageArtwork)
        val soundWaves = itemView.findViewById<ImageView>(R.id.soundWaves)
        fun bind(item: MusicItem, isSelected: Boolean) {
            itemView.findViewById<TextView>(R.id.tvTrackName).text = item.trackName
            itemView.findViewById<TextView>(R.id.tvArtistName).text = item.artistName
            itemView.findViewById<TextView>(R.id.tvAlbumName).text = item.collectionName

            Glide.with(itemView.context)
                .load(item.artworkUrl100)
                .placeholder(android.R.color.darker_gray)
                .into(image)
            itemView.setOnClickListener {
                onItemClick(item)
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val previousIndex = selectedIndex
                    selectedIndex = position
                    notifyItemChanged(previousIndex)
                    notifyItemChanged(position)
                }
            }
            soundWaves.visibility = if (isSelected) View.VISIBLE else View.GONE
        }
    }
}
