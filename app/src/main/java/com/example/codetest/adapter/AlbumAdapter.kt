package com.example.codetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.R
import com.example.codetest.model.response.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_album.view.*
import kotlinx.android.synthetic.main.list_album.view.*

class AlbumAdapter(
    context: Context,
    albumList: List<Album>
): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var mContext: Context = context
    var displayList: List<Album>? = albumList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        holder.bind(holder)
    }

    override fun getItemCount(): Int {
        displayList?.let {
            return it.size
        }
        return 0
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val album = itemView.album


        fun bind(holder: ViewHolder){

            var item = displayList?.get(adapterPosition)

            item?.collectionName?.let { holder.album.setCollectionName(it) }
            item?.artistName?.let { holder.album.setArtist(it) }
            item?.releaseDate?.let { holder.album.setReleaseDate(it.split("T")[0]) }
            item?.artworkUrl100?.let { Picasso.get().load(it).into(holder.album.iv_collection_img) }


        }

    }
}