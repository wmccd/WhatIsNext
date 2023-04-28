package com.wmccd.record_domain.internal

import com.wmccd.common_exceptions.external.InvalidRandomRecordException
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import com.wmccd.common_models.external.records.RecordModel

class RandomRecordSelector {

    internal fun select(
        recentRecords: List<RecentRandomRecordModel>,
        windowSize: Int,
        records: List<RecordModel>,
        report:() -> Unit
    ): RecordModel{
        val choices = generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        if(choices.isEmpty()){
            report()
            throw InvalidRandomRecordException("No random record choice available")
        }

        val random = (choices.indices).random()
        return choices[random]
    }


    internal fun generateEligibleRandomRecordChoices(
        recentRecords: List<RecentRandomRecordModel>,
        windowSize: Int,
        records: List<RecordModel>
    ): List<RecordModel> {

        val mostRecentRecords = recentRecords.sortedByDescending { it.timestamp }.take(windowSize)
        val recentIdList = arrayListOf<Long>()
        mostRecentRecords.map {
            recentIdList.add(it.id)
        }
        val choices = records.filterNot {
            recentIdList.contains(it.id)
        }
        return choices
    }

}


