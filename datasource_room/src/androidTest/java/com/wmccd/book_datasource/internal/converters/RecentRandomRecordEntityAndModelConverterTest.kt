package com.wmccd.book_datasource.internal.converters

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_datasource.internal.records.RecentRandomRecordEntity
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecentRandomRecordEntityAndModelConverterTest {

    @Test
    fun convert_modelToEntity_correctValues(){

        //assemble
        val converter = RecentRandomRecordEntityAndModelConverter()
        val expectedId = 1L
        val expectedTimestamp = 2L
        val model = RecentRandomRecordModel(
            id = expectedId,
            timestamp = expectedTimestamp,
        )

        //act
        val actual = converter.convert( recentRandomRecordModel = model)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedTimestamp, actual.timestamp)
    }

    @Test
    fun convert_entityToModel_correctValues(){

        //assemble
        val converter = RecentRandomRecordEntityAndModelConverter()
        val expectedId = 1L
        val expectedTimestamp = 2L
        val entity = RecentRandomRecordEntity(
            id = expectedId,
            timestamp = expectedTimestamp
        )

        //act
        val actual = converter.convert( recentRandomRecordEntity = entity)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedTimestamp, actual.timestamp)
    }


}