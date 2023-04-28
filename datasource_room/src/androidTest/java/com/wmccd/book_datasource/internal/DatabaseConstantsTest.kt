package com.wmccd.book_datasource.internal

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseConstantsTest {

    @Test
    fun whatIsNextDatabase_database_correctValues(){
        Assert.assertEquals( "whatIsNext.db", DatabaseConstants.WhatIsNextDatabaseConstants.NAME)
        Assert.assertEquals( 1, DatabaseConstants.WhatIsNextDatabaseConstants.VERSION)
    }

    @Test
    fun whatIsNextDatabase_tables_correctValues(){
        Assert.assertEquals( "records", DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Records.NAME)
        Assert.assertEquals( "recent_records", DatabaseConstants.WhatIsNextDatabaseConstants.Tables.RecentRandomRecords.NAME)
        Assert.assertEquals( "books", DatabaseConstants.WhatIsNextDatabaseConstants.Tables.Books.NAME)
    }

}