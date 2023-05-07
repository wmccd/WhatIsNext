package com.wmccd.record_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.datasource_datastore.external.RecentRandomRecordSettingsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RecentRandomRecordSettingsRepositoryImpl  (
    private val dataSource: RecentRandomRecordSettingsDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): RecentRandomRecordSettingsRepository {


    //override val recentRandomRecordsWindowSize: Flow<Int> = dataSource.recentRandomRecordsWindowSize
    override val recentRandomRecordsWindowSize: Flow<Int> = dataSource.recentRandomRecordsWindowSize.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: recent random records window size",
                key = "size",
                value = it
            )
        )
        it
    }


    override suspend fun recentRandomRecordsWindowSize(size: Int) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: set recent random records window size",
                key = "size",
                value = size
            )
        )
        withContext(dispatcher) {
            dataSource.recentRandomRecordsWindowSize(size = size)
        }
    }
}