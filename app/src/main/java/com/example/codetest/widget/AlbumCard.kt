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
    private var isBookmark: Boolean

    init {
        inflate(context, R.layout.card_album, this)

        val style = context.obtainStyledAttributes(attributeSet, R.styleable.AlbumCard)

        collectionName = style.getString(R.styleable.AlbumCard_collection_name) ?: ""
        artist = style.getString(R.styleable.AlbumCard_artist) ?: ""
        releaseDate = style.getString(R.styleable.AlbumCard_release_date) ?: ""
        isBookmark = style.getBoolean(R.styleable.AlbumCard_is_bookmark, false)

        style.recycle()
        initView()
    }

    private fun initView() {
        setCollectionName(collectionName)
        setArtist(artist)
        setReleaseDate(releaseDate)
        setIsBookmark(isBookmark)

    }

    fun setCollectionName(input: String){
        tv_collection.text = input
    }

    fun setArtist(input: String){
        tv_artist.text = input
    }

    fun setReleaseDate(input: String){
        tv_release_date.text = input
    }

    fun setIsBookmark(input: Boolean){
        if (input){
            iv_bookmark.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            iv_bookmark.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}