package com.wmccd.book_datasource.internal.records

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.wmccd.book_datasource.internal.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME,
    indices = [
        Index("id", unique = true),
        Index("act", unique = false)
    ]
)
internal data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val act: String = "",
    val title: String = "",
)