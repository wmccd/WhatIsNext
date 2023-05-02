package com.wmccd.whatisnext

import android.app.Application
import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueReporterImpl
import com.wmccd.common_models_types.external.models.application.BuildConfigurationModel
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

        val appAnalogueReporter = AnalogueReporterImpl()
        val appRemoteConfiguration = RemoteConfigurationImpl()

        //Inject this into any VM/UseCase that requires.
        //Version Code not available in libraries.
        val appBuildConfig = BuildConfigurationModel(
            debug = BuildConfig.DEBUG,
            buildType = BuildConfig.BUILD_TYPE,
            version_code = BuildConfig.VERSION_CODE
        )

    }
}