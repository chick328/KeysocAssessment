package com.example.codetest.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    val allDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    override fun onCleared() {
        super.onCleared()
        allDisposable.clear()
    }


}