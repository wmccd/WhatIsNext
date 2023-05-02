package com.wmccd.book_datasource.internal.converters

import com.wmccd.book_datasource.internal.records.RecordEntity
import com.wmccd.common_models_types.external.models.records.RecordModel

internal class RecordEntityAndModelConverter {

    fun convert(recordEntity: RecordEntity): RecordModel {
        return RecordModel(
            id = recordEntity.id ?: -1,
            act = recordEntity.act,
            title = recordEntity.title
        )
    }

    fun convert(recordModel: RecordModel): RecordEntity {
        return RecordEntity(
            id = if(recordModel.id == -1L) null else recordModel.id,
            act = recordModel.act,
            title = recordModel.title
        )
    }
}