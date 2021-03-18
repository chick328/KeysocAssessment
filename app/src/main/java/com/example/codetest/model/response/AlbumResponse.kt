package com.example.codetest.model.response

data class AlbumResponse (
    val resultCount: Long,
    val results: List<Result>
)

data class Result(
    val wrapperType: String,
    val collectionType: String,
    val artistID: Long,
    val collectionID: Long,
    val amgArtistID: Long? = null,
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
)