package com.wmccd.book_datasource.internal.records

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.wmccd.book_datasource.internal.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.WhatIsNextDatabaseConstants.Tables.RecentRandomRecords.NAME,
    indices = [
        Index("timestamp", unique = true)
    ]
)

internal data class RecentRandomRecordEntity(
    @PrimaryKey val timestamp: Long = 0,
    val id: Long = 0
)