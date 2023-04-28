package com.wmccd.record_domain.external.usescases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models.external.records.RecordModel
import com.wmccd.record_domain.internal.RecordChecker
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class InsertRecordUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): InsertRecordUseCase {
    override suspend fun execute(recordModel: RecordModel) {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: record insert"
            )
        )

        RecordChecker().check(recordModel = recordModel)

        recordRepository.insert(recordModel = recordModel)
    }




}