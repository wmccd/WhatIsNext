package com.wmccd.book_datasource.internal.records

import androidx.room.*
import com.wmccd.book_datasource.internal.DatabaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecordStore {
    @Query("SELECT * FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME} ORDER BY act ASC")
    fun fetchAll(): Flow<List<RecordEntity>>

    @Query("SELECT COUNT(*) FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME}")
    fun count(): Flow<Int>

    @Insert
    suspend fun insert(recordEntity: RecordEntity)

    @Update
    suspend fun update(recordEntity: RecordEntity): Int

    @Query("DELETE FROM  ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME} WHERE id = :id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME}")
    suspend fun deleteAll()
}

