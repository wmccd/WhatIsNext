package com.wmccd.book_datasource.internal.converters

import com.wmccd.book_datasource.internal.records.RecentRandomRecordEntity
import com.wmccd.common_models.external.records.RecentRandomRecordModel

internal class RecentRandomRecordEntityAndModelConverter {

    fun convert(recentRandomRecordEntity: RecentRandomRecordEntity): RecentRandomRecordModel {
        return RecentRandomRecordModel(
            id = recentRandomRecordEntity.id,
            timestamp = recentRandomRecordEntity.timestamp
        )
    }

    fun convert(recentRandomRecordModel: RecentRandomRecordModel): RecentRandomRecordEntity {
        return RecentRandomRecordEntity(
            id = recentRandomRecordModel.id,
            timestamp = recentRandomRecordModel.timestamp
        )
    }
}