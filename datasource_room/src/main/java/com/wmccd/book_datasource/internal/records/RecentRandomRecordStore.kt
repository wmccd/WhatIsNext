package com.wmccd.book_datasource.internal.records

import androidx.room.*
import com.wmccd.book_datasource.internal.DatabaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecentRandomRecordStore {
    @Query("SELECT * FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.RecentRandomRecords.NAME} ORDER BY timestamp DESC")
    fun fetchAll(): Flow<List<RecentRandomRecordEntity>>

    @Query("SELECT COUNT(*) FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.RecentRandomRecords.NAME}")
    fun count(): Flow<Int>

    @Insert
    suspend fun insert(recentRandomRecordEntity: RecentRandomRecordEntity)

    @Query("DELETE FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.RecentRandomRecords.NAME}")
    suspend fun deleteAll()
}

