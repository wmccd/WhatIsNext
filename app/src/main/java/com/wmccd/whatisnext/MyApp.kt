package com.wmccd.whatisnext

import android.app.Application
import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueReporterImpl
import com.wmccd.configuration.external.RemoteConfiguration
import com.wmccd.configuration.external.RemoteConfigurationImpl

class MyApp : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        var instance: MyApp? = null
            private set

        val context: Context
            get() = instance!!

        val analogueReporter = AnalogueReporterImpl()
        val remoteConfiguration = RemoteConfigurationImpl()
    }
}