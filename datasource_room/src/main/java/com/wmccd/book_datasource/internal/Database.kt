package com.wmccd.book_datasource.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wmccd.book_datasource.internal.books.BookEntity
import com.wmccd.book_datasource.internal.books.BookStore
import com.wmccd.book_datasource.internal.records.RecentRandomRecordEntity
import com.wmccd.book_datasource.internal.records.RecentRandomRecordStore
import com.wmccd.book_datasource.internal.records.RecordEntity
import com.wmccd.book_datasource.internal.records.RecordStore

private const val DB_NAME = DatabaseConstants.WhatIsNextDatabaseConstants.NAME

@Database(
    entities = [RecordEntity::class, RecentRandomRecordEntity::class, BookEntity::class],
    version = DatabaseConstants.WhatIsNextDatabaseConstants.VERSION
)
internal abstract class WhatIsNextDatabase : RoomDatabase() {
    abstract fun recordStore(): RecordStore
    abstract fun recentRandomRecordStore(): RecentRandomRecordStore
    abstract fun bookStore(): BookStore

    companion object {
        fun instance(context: Context) = Room
            .databaseBuilder(context, WhatIsNextDatabase::class.java, DB_NAME)
            .build()
    }
}