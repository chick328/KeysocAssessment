package com.example.codetest.network

import com.example.codetest.model.response.AlbumResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface ApiService {

    @GET("search?term=jack+johnson&entity=album")
    fun getAlbum(): Observable<Response<AlbumResponse>>
}