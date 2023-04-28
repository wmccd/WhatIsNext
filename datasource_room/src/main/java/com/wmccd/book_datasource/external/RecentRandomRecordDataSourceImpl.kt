package com.wmccd.book_datasource.external

import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.book_datasource.internal.converters.RecentRandomRecordEntityAndModelConverter
import com.wmccd.common_models.external.records.RecentRandomRecordModel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentRandomRecordDataSourceImpl(
    private val context: Context,
    private val analogueReporter: AnalogueReporter
): RecentRandomRecordDataSource {

    private val recentRandomRecordStore = WhatIsNextDatabase.instance(context = context).recentRandomRecordStore()
    private val converter = RecentRandomRecordEntityAndModelConverter()

    override val recentRandomRecords: Flow<List<RecentRandomRecordModel>> = recentRandomRecordStore.fetchAll().map { entityList ->
        val modelList = arrayListOf<RecentRandomRecordModel>()
        entityList.forEach { modelList.add(
            converter.convert( recentRandomRecordEntity = it)
        ) }
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: recent random records flow update",
            )
        )
        modelList
    }

    override val count: Flow<Int> = recentRandomRecordStore.count().map {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: recent random records count flow update",
                key = "count",
                value = it
            )
        )
        it
    }

    override suspend fun insert(recentRandomRecordModel: RecentRandomRecordModel) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: recent random records insert",
                key = "model",
                value = recentRandomRecordModel
            )
        )
        recentRandomRecordStore.insert(
            converter.convert(recentRandomRecordModel = recentRandomRecordModel)
        )
    }


}