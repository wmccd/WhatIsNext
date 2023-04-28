package com.wmccd.record_domain.internal

import com.wmccd.common_exceptions.external.model_validation.InvalidRecordActException
import com.wmccd.common_exceptions.external.model_validation.InvalidRecordTitleException
import com.wmccd.common_models.external.records.RecordModel

internal class RecordChecker {

    fun check(recordModel: RecordModel){
        if(recordModel.act.isBlank())
            throw InvalidRecordActException(message = recordModel.toString())

        if(recordModel.title.isBlank())
            throw InvalidRecordTitleException(message = recordModel.toString())
    }
}