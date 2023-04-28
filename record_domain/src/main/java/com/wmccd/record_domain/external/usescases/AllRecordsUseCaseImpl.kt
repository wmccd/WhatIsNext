package com.wmccd.record_domain.external.usescases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models.external.records.RecordModel
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AllRecordsUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): AllRecordsUseCase {

    //override suspend fun execute(): Flow<List<RecordModel>> = recordRepository.records

    override suspend fun execute(): Flow<List<RecordModel>> = recordRepository.records.map {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: record flow updates",
            )
        )
        it
    }
}