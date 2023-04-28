package com.wmccd.book_datasource.internal.books

import androidx.room.*
import com.wmccd.book_datasource.internal.DatabaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BookStore {
    @Query("SELECT * FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME} ORDER BY author ASC")
    fun fetchAll(): Flow<List<BookEntity>>

    @Query("SELECT COUNT(*) FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME}")
    fun count(): Flow<Int>

    @Insert
    suspend fun insert(bookEntity: BookEntity)

    @Update
    suspend fun update(bookEntity: BookEntity): Int

    @Query("DELETE FROM  ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME} WHERE id = :id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM ${DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME}")
    suspend fun deleteAll()
}

