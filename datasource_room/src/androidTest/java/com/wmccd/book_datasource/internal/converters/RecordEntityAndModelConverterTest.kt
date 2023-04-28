package com.wmccd.book_datasource.internal.converters

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_datasource.internal.records.RecordEntity
import com.wmccd.common_models.external.records.RecordModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecordEntityAndModelConverterTest {

    @Test
    fun convert_modelToEntity_correctValues(){

        //assemble
        val converter = RecordEntityAndModelConverter()
        val expectedId = 1L
        val expectedAct = "A"
        val expectedTitle = "B"
        val model = RecordModel(
            id = expectedId,
            act = expectedAct,
            title = expectedTitle
        )

        //act
        val actual = converter.convert( recordModel = model)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedAct, actual.act)
        Assert.assertEquals(expectedTitle, actual.title)
    }

    @Test
    fun convert_entityToModel_correctValues(){

        //assemble
        val converter = RecordEntityAndModelConverter()
        val expectedId = 1L
        val expectedAct = "A"
        val expectedTitle = "B"
        val entity = RecordEntity(
            id = expectedId,
            act = expectedAct,
            title = expectedTitle
        )

        //act
        val actual = converter.convert( recordEntity = entity)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedAct, actual.act)
        Assert.assertEquals(expectedTitle, actual.title)
    }


}