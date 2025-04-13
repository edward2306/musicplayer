package com.example.musicplayer.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MusicItem
import com.example.musicplayer.R
import com.example.musicplayer.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MusicViewModel by viewModel()
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: RecyclerView = findViewById(R.id.recyclerView)
        val btnPlay = findViewById<ImageView>(R.id.ivPlay)
        val btnPause = findViewById<ImageView>(R.id.ivPause)
        val adapter = MusicAdapter(
            onItemClick = { item ->
                playMusic(item)
                btnPlay.visibility = View.GONE
                btnPause.visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.llMusicIndicator).visibility = View.VISIBLE
                btnPlay.setOnClickListener {
                    btnPlay.visibility = View.GONE
                    btnPause.visibility = View.VISIBLE
                    playMusic(item)
                }
                btnPause.setOnClickListener {
                    btnPause.visibility = View.GONE
                    btnPlay.visibility = View.VISIBLE
                    pauseMusic(item)
                }
            },
            currentlyPlayingId = viewModel.currentlyPlayingId
        )
        viewModel.music()
        viewModel.musics.observe(this) {
            adapter.submitList(it)
        }
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adapter
        findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) viewModel.search(query)
                return true
            }
            override fun onQueryTextChange(newText: String?) : Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.search("music")
                } else {
                    viewModel.search(newText)
                }
                return true
            }
        })
    }
    private fun playMusic(item: MusicItem) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(item.previewUrl)
            prepare()
            start()
        }
        viewModel.setPlaying(item.trackId)
    }

    private fun pauseMusic(item: MusicItem) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(item.previewUrl)
            prepare()
            pause()
        }
    }
}