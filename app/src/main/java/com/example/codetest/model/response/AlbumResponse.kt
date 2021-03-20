package com.example.codetest.model.response

data class AlbumResponse (
    val resultCount: Long,
    val results: List<Album>
)

data class Album(
    val wrapperType: String,
    val collectionType: String,
    val artistId: Long,
    val collectionId: Long,
    val amgArtistId: Long? = null,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewURL: String,
    val collectionViewURL: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: String,
    val trackCount: Long,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String,
    val contentAdvisoryRating: String? = null
){
    override fun equals(other: Any?): Boolean {

        other as Album

        if (artistName != other.artistName){
            return false
        }

        if (collectionName != other.collectionName){
            return false
        }

        if (artworkUrl100 != other.artworkUrl100){
            return false
        }

        if (collectionId != other.collectionId){
            return false
        }

        return true
    }
}