package com.wmccd.record_domain.external.usescases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.records.RecordModel
import com.wmccd.record_domain.internal.RecordChecker
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UpdateRecordUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): UpdateRecordUseCase {
    override suspend fun execute(recordModel: RecordModel): Int {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: record update",
                key = "",
                value = ""
            )
        )

        RecordChecker().check(recordModel = recordModel)

        return recordRepository.update(recordModel = recordModel)
    }




}