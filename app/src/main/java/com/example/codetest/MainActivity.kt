package com.example.codetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.adapter.AlbumAdapter
import com.example.codetest.model.response.Album
import com.example.codetest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeViewModel()

        mainViewModel.getAlbum()

    }

    private fun subscribeViewModel() {
        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MainViewModel::class.java]

        mainViewModel.albumData.observe(this, Observer {
            setUpRecyclerView(it)
        })
    }

    private fun setUpRecyclerView(albums: List<Album>) {
        rv_album.adapter = AlbumAdapter(applicationContext, albums)
        rv_album.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
    }
}