package com.wmccd.book_datasource.internal

internal class DatabaseConstants {

    object WhatIsNextDatabaseConstants{
        const val NAME = "whatIsNext.db"
        const val VERSION = 1

        object Tables{
            object Records{
                const val NAME = "records"
            }
            object RecentRandomRecords{
                const val NAME = "recent_records"
            }
            object Books{
                const val NAME = "books"
            }
        }
    }
}
