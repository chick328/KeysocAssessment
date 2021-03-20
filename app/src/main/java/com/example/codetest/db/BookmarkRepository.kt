package com.example.codetest.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    val bookmarkedIds: Flow<List<Long>> = bookmarkDao.getIds()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bookmark: Bookmark){
        bookmarkDao.insert(bookmark)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(bookmark: Bookmark){
        bookmarkDao.delete(bookmark)
    }
}