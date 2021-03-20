package com.example.codetest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.R
import com.example.codetest.listener.OnBookmarkClickListener
import com.example.codetest.model.response.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_album.view.*
import kotlinx.android.synthetic.main.list_album.view.*

class AlbumAdapter(
    context: Context,
    listener: OnBookmarkClickListener
): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var mContext: Context = context
    var mDisplayList: List<Album> = listOf()
    var mBookmarkList: List<Long> = listOf()
    var mListener: OnBookmarkClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        holder.bind(holder)
    }

    override fun getItemCount(): Int {
        mDisplayList.let {
            return it.size
        }
        return 0
    }

    fun submitList(albums: List<Album>){
        val oldList = mDisplayList
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            AlbumDiffCallback(
                oldList,
                albums
            )
        )
        mDisplayList = albums
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateBookmark(bookmarks: List<Long>){
        mBookmarkList = bookmarks
        notifyDataSetChanged()
    }

    class AlbumDiffCallback(
        var oldAlbumList: List<Album>,
        var newAlbumList: List<Album>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return  oldAlbumList.size
        }

        override fun getNewListSize(): Int {
            return  newAlbumList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldAlbumList[oldItemPosition] === newAlbumList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldAlbumList[oldItemPosition] == newAlbumList[newItemPosition]
        }

    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val album = itemView.album


        fun bind(holder: ViewHolder){

            var item = mDisplayList.get(adapterPosition)

            item.collectionName.let { holder.album.setCollectionName(it) }
            item.artistName.let { holder.album.setArtist(it) }
            item.releaseDate.let { holder.album.setReleaseDate(it.split("T")[0]) }
            item.artworkUrl100.let { Picasso.get().load(it).into(holder.album.iv_collection_img) }

            mBookmarkList.let {
                if (it.contains(item.collectionId)){
                    holder.album.setIsBookmark(true)
                } else {
                    holder.album.setIsBookmark(false)
                }
            }

            holder.album.iv_bookmark.setOnClickListener {
                mBookmarkList.let { list ->
                    if (list.contains(item.collectionId)){
                        item.collectionId.let { mListener.OnBookmarkClick(it, true) }
                    } else {
                        item.collectionId.let { mListener.OnBookmarkClick(it, false) }
                    }
                }
            }

        }

    }

}