package com.example.codetest.viewModel

import android.app.Application
import android.util.Log
import com.example.codetest.model.response.AlbumResponse
import com.example.codetest.network.ApiService
import com.example.codetest.network.ApiServiceImpl
import io.reactivex.rxjava3.observers.DisposableObserver
import retrofit2.Response

class MainViewModel(application: Application): BaseViewModel(application) {

    fun getAlbum(){
        allDisposable.addAll(
            ApiServiceImpl.getAlbum().subscribeWith(object: DisposableObserver<Response<AlbumResponse>>() {
                override fun onNext(t: Response<AlbumResponse>?) {
                    Log.d("asd", t?.body()?.results.toString())
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                    Log.d("asd", "end")

                }

            }

            )
        )
    }
}