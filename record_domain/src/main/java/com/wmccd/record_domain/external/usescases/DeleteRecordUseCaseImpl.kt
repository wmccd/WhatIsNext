package com.wmccd.record_domain.external.usescases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DeleteRecordUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
): DeleteRecordUseCase {
    override suspend fun execute(id: Long) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: record delete",
                key = "id",
                value = id
            )
        )
        recordRepository.delete(id = id)
    }


}