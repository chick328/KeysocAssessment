package com.example.codetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.adapter.AlbumAdapter
import com.example.codetest.application.MyApplication
import com.example.codetest.model.response.Album
import com.example.codetest.viewModel.DbViewModel
import com.example.codetest.viewModel.DbViewModelFactory
import com.example.codetest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModels()
    val dbViewModel: DbViewModel by viewModels {
        DbViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeViewModel()

        mainViewModel.getAlbum()

    }

    private fun subscribeViewModel() {

        mainViewModel.albumData.observe(this, Observer {
            setUpRecyclerView(it)
        })

        dbViewModel.bookmarkedIds.observe(this, Observer {

        })

    }

    private fun setUpRecyclerView(albums: List<Album>) {
        rv_album.adapter = AlbumAdapter(applicationContext, albums)
        rv_album.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
    }
}