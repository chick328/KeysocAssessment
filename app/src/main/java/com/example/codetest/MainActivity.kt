package com.example.codetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.adapter.AlbumAdapter
import com.example.codetest.application.MyApplication
import com.example.codetest.constant.Page
import com.example.codetest.db.Bookmark
import com.example.codetest.listener.OnBookmarkClickListener
import com.example.codetest.model.response.Album
import com.example.codetest.viewModel.DbViewModel
import com.example.codetest.viewModel.DbViewModelFactory
import com.example.codetest.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnBookmarkClickListener {

    val mainViewModel: MainViewModel by viewModels()
    val dbViewModel: DbViewModel by viewModels {
        DbViewModelFactory((application as MyApplication).repository)
    }

    private var bookmarks: List<Long> = listOf()
    private var albums: List<Album> = listOf()
    private var adapter: AlbumAdapter? = null
    private var pageType: Page = Page.ALBUM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNav()

        setUpRecyclerView()

        observeMainViewModel()

        observeDbViewModel()

        mainViewModel.getAlbum()

    }


    private fun initBottomNav() {
        bottom_navigation.setOnNavigationItemSelectedListener(btmNavListener)
    }

    private var btmNavListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.album -> {
                    pageType = Page.ALBUM
                    mainViewModel.getAlbum()
                }
                R.id.bookmark -> {
                    pageType = Page.BOOKMARK
                    mainViewModel.getBookmark(albums, bookmarks)
                }
            }
            true
        }

    private fun observeMainViewModel() {

        mainViewModel.albumData.observe(this, Observer {
            albums = it
            submitList(it)
            updateBookmark(bookmarks)
        })


        mainViewModel.bookmarkData.observe(this, Observer {
            submitList(it)
            updateBookmark(bookmarks)
        })

    }

    private fun observeDbViewModel() {

        dbViewModel.bookmarkedIds.observe(this, Observer {

            bookmarks = it
            updateBookmark(it)

            when(pageType){
                Page.ALBUM -> {
                }

                Page.BOOKMARK -> {
                    mainViewModel.getBookmark(albums, bookmarks)
                }
            }


        })

    }


    private fun setUpRecyclerView() {
        adapter = AlbumAdapter(applicationContext, this)
        rv_album.adapter = adapter
        rv_album.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
    }


    private fun submitList(albums: List<Album> = listOf()){
        adapter?.submitList(albums)
    }


    private fun updateBookmark(bookmarkList: List<Long> = listOf()){
        adapter?.updateBookmark(bookmarkList)
    }


    override fun OnBookmarkClick(collectionId: Long, isBookmarked: Boolean) {
        if (isBookmarked) {
            dbViewModel.delete(Bookmark(collectionId))
        }else {
            dbViewModel.insert(Bookmark(collectionId))
        }

    }
}