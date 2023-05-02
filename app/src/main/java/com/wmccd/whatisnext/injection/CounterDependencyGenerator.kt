package com.wmccd.whatisnext.injection

import com.wmccd.book_datasource.external.BookDataSourceImpl
import com.wmccd.book_datasource.external.RecordDataSourceImpl
import com.wmccd.book_domain.external.usecases.BookCountUseCase
import com.wmccd.book_domain.external.usecases.BookCountUseCaseImpl
import com.wmccd.book_repository.external.BookRepositoryImpl
import com.wmccd.home_presentation.external.homescreen.CounterViewModelImpl
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import com.wmccd.record_domain.external.usescases.RecordCountUseCaseImpl
import com.wmccd.record_repository.external.RecordRepositoryImpl
import com.wmccd.whatisnext.MyApp
import kotlinx.coroutines.Dispatchers

class CounterDependencyGenerator {

    fun generate(): CounterViewModelImpl{
        return CounterViewModelImpl(
            bookCountUseCase = generateBookCountUseCase(),
            recordCountUseCase = generateRecordCountUseCase(),
            analogueReporter = MyApp.appAnalogueReporter,
            remoteConfiguration = MyApp.appRemoteConfiguration
        )
    }

    private fun generateBookCountUseCase(): BookCountUseCase {
        val bookDataSource = BookDataSourceImpl(
            context = MyApp.context,
            analogueReporter = MyApp.appAnalogueReporter
        )

        val bookRepository = BookRepositoryImpl(
            dataSource = bookDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )

        return BookCountUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }

    private fun generateRecordCountUseCase(): RecordCountUseCase {
        val recordDataSource = RecordDataSourceImpl(
            context = MyApp.context,
            analogueReporter = MyApp.appAnalogueReporter
        )

        val recordRepository = RecordRepositoryImpl(
            dataSource = recordDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )

        return RecordCountUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }
}