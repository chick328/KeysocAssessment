package com.example.codetest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class Bookmark (
    @PrimaryKey @ColumnInfo(name = "collectionId") val collectionId: Long

)