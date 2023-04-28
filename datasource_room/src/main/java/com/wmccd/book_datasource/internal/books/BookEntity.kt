package com.wmccd.book_datasource.internal.books

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.wmccd.book_datasource.internal.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME,
    indices = [
        Index("id", unique = true),
        Index("author", unique = false),
    ]
)
internal data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val author: String = "",
    val title: String = "",
)