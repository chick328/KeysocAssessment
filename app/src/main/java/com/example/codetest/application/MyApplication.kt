package com.example.codetest.application

import android.app.Application
import com.example.codetest.db.BookmarkDatabase
import com.example.codetest.db.BookmarkRepository

class MyApplication: Application(){
    val database by lazy { BookmarkDatabase.getDatabase(this) }
    val repository by lazy { BookmarkRepository(database.bookmarkDao()) }
}