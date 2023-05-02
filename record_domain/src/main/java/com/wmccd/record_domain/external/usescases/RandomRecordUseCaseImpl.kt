package com.wmccd.record_domain.external.usescases


import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.records.RecordModel
import com.wmccd.record_domain.internal.RandomRecordSelector
import com.wmccd.record_repository.external.RecentRandomRecordRepository
import com.wmccd.record_repository.external.RecentRandomRecordSettingsRepository
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class RandomRecordUseCaseImpl(
    private val recordRepository: RecordRepository,
    private val recentRandomRecordsRepository: RecentRandomRecordRepository,
    private val recentRandomRecordSettingsRepository: RecentRandomRecordSettingsRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
):RandomRecordUseCase {

    override suspend fun execute(): RecordModel {

        var randomRecord = RecordModel()
        combine(
            recordRepository.records,
            recentRandomRecordsRepository.recentRandomRecords,
            recentRandomRecordSettingsRepository.recentRandomRecordsWindowSize
        ) { records, recentRecords, windowSize ->
            randomRecord = withContext(dispatcher) {
                RandomRecordSelector().select(
                    recentRecords = recentRecords,
                    windowSize = windowSize,
                    records = records,
                    report = ::report
                )
            }
        }
        return randomRecord
    }

    private fun report(){
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "No random record choice available"
            )
        )
    }


}