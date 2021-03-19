package com.example.codetest.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.codetest.db.BookmarkRepository
import com.example.codetest.model.response.Album
import com.example.codetest.model.response.AlbumResponse
import com.example.codetest.network.ApiService
import com.example.codetest.network.ApiServiceImpl
import io.reactivex.rxjava3.observers.DisposableObserver
import retrofit2.Response

class MainViewModel(application: Application): BaseViewModel(application) {
    val albumData = MutableLiveData<List<Album>>()

    fun getAlbum(){
        allDisposable.addAll(
            ApiServiceImpl.getAlbum().subscribeWith(object: DisposableObserver<Response<AlbumResponse>>() {
                override fun onNext(t: Response<AlbumResponse>) {
                    if (t.isSuccessful){
                        albumData.postValue(t.body()?.results)

                    }
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {

                }

            })
        )
    }
}