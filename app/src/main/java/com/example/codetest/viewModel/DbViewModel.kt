package com.example.codetest.viewModel

import androidx.lifecycle.*
import com.example.codetest.db.Bookmark
import com.example.codetest.db.BookmarkRepository
import kotlinx.coroutines.launch

class DbViewModel(private val repository: BookmarkRepository): ViewModel() {

    val bookmarkedIds: LiveData<List<String>> = repository.bookmarkedIds.asLiveData()

    fun insert(bookmark: Bookmark) = viewModelScope.launch {
        repository.insert(bookmark)
    }

    fun delete(bookmark: Bookmark) = viewModelScope.launch {
        repository.delete(bookmark)
    }
}

class DbViewModelFactory(private val repository: BookmarkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DbViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DbViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}