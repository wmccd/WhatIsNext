package com.wmccd.book_datasource.external

import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.book_datasource.internal.converters.RecordEntityAndModelConverter
import com.wmccd.common_models_types.external.models.records.RecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordDataSourceImpl(
    private val context: Context,
    private val analogueReporter: AnalogueReporter
): RecordDataSource {

    private val recordStore = WhatIsNextDatabase.instance(context = context).recordStore()
    private val converter = RecordEntityAndModelConverter()

    override val records: Flow<List<RecordModel>> = recordStore.fetchAll().map { recordEntityList ->
        val modelList = arrayListOf<RecordModel>()
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: records flow update1",
            )
        )
        recordEntityList.forEach {
            modelList.add(
                converter.convert( recordEntity = it)
            )
        }
        modelList
    }

    override val count: Flow<Int> = recordStore.count().map {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: records count flow update",
                key = "count",
                value = it
            )
        )
        it
    }

    override suspend fun insert(recordModel: RecordModel) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: record insert",
                key = "model",
                value = recordModel
            )
        )
        recordStore.insert(
            converter.convert(recordModel = recordModel)
        )
    }

    override suspend fun update(recordModel: RecordModel): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: record update",
                key = "model",
                value = recordModel
            )
        )
        return recordStore.update(
            converter.convert(recordModel = recordModel)
        )
    }

    override suspend fun delete(id: Long): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: record delete",
                key = "id",
                value = id
            )
        )
        return recordStore.delete( id = id)
    }
}