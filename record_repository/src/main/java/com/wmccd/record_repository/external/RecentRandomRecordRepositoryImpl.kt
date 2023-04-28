package com.wmccd.record_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.external.RecentRandomRecordDataSource
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RecentRandomRecordRepositoryImpl(
    private val dataSource: RecentRandomRecordDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): RecentRandomRecordRepository {

    //override val recentRandomRecords: Flow<List<BookModel>> = dataSource.recentRandomRecords
    override val recentRandomRecords: Flow<List<RecentRandomRecordModel>> = dataSource.recentRandomRecords.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: recent random records flow update",
            )
        )
        it
    }

    //override val count: Flow<Int> = dataSource.count
    override val count: Flow<Int> = dataSource.count.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: recent random records count flow update",
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
                whatHappened = "Repository: recent random records insert",
                key = "model",
                value = recentRandomRecordModel
            )
        )

        withContext(dispatcher){
            dataSource.insert(recentRandomRecordModel = recentRandomRecordModel)
        }
        return
    }




}