package com.wmccd.record_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.external.RecordDataSource
import com.wmccd.common_models_types.external.models.records.RecordModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RecordRepositoryImpl(
    private val dataSource: RecordDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): RecordRepository {

    //override val records: Flow<List<RecordModel>> = dataSource.records
    override val records: Flow<List<RecordModel>> = dataSource.records.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: record flow update",
            )
        )
        it
    }
    //override val count: Flow<Int> = dataSource.count
    override val count: Flow<Int> = dataSource.count.map{

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: record count flow update",
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
                whatHappened = "Repository: record insert",
                key = "model",
                value = recordModel
            )
        )
        withContext(dispatcher){
            dataSource.insert(recordModel = recordModel)
        }
        return
    }

    override suspend fun update(recordModel: RecordModel): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: record update",
                key = "model",
                value = recordModel
            )
        )
        var count = 0
        withContext(dispatcher){
            count = dataSource.update(recordModel = recordModel)
        }
        return count
    }

    override suspend fun delete(id: Long): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: record delete",
                key = "id",
                value = id
            )
        )
        var count = 0
        withContext(dispatcher){
            count = dataSource.delete(id = id)
        }
        return count
    }

}