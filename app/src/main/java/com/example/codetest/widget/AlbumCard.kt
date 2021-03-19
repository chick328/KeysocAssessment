package com.example.codetest.widget

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.example.codetest.R
import kotlinx.android.synthetic.main.card_album.view.*

class AlbumCard constructor(
    context: Context, attributeSet: AttributeSet? = null
): CardView(context, attributeSet){

    private var collectionName: String
    private var artist: String
    private var releaseDate: String

    init {
        inflate(context, R.layout.card_album, this)

        val style = context.obtainStyledAttributes(attributeSet, R.styleable.AlbumCard)

        collectionName = style.getString(R.styleable.AlbumCard_collection_name) ?: ""
        artist = style.getString(R.styleable.AlbumCard_artist) ?: ""
        releaseDate = style.getString(R.styleable.AlbumCard_release_date) ?: ""

        style.recycle()
        initView()
    }

    private fun initView() {
        setCollectionName(collectionName)
        setArtist(artist)
        setReleaseDate(releaseDate)

    }

    fun setCollectionName(input: String) {
        tv_collection.text = input
    }

    fun setArtist(input: String) {
        tv_artist.text = input
    }

    fun setReleaseDate(input: String) {
        tv_release_date.text = input
    }
}