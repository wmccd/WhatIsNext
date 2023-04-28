package com.wmccd.record_domain.external.usescases

interface DeleteRecordUseCase {
    suspend fun execute(id:Long)
}

