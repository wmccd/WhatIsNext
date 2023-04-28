package com.wmccd.record_domain.external

import com.wmccd.record_domain.external.usescases.AllRecordsUseCase
import com.wmccd.record_domain.external.usescases.DeleteRecordUseCase
import com.wmccd.record_domain.external.usescases.InsertRecordUseCase
import com.wmccd.record_domain.external.usescases.RandomRecordUseCase
import com.wmccd.record_domain.external.usescases.UpdateRecordUseCase

data class RecordUseCases(
    val allRecordsUseCase: AllRecordsUseCase,
    val deleteRecordUseCase: DeleteRecordUseCase,
    val insertRecordUseCase: InsertRecordUseCase,
    val randomRecordUseCase: RandomRecordUseCase,
    val updateRecordUseCase: UpdateRecordUseCase,
)
