package com.wmccd.record_domain.external.usescases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordCountUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): RecordCountUseCase {

    //override suspend fun execute(): Flow<Int> = recordRepository.count

    override suspend fun execute(): Flow<Int> = recordRepository.count.map {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: book count flow",
                key = "count",
                value = it
            )
        )

        it
    }

}