package com.wmccd.analogue_reporter.external

import android.util.Log
import com.wmccd.analogue_reporter.internal.AirshipReporter
import com.wmccd.analogue_reporter.internal.AmplitudeReporter
import com.wmccd.analogue_reporter.internal.DataDogReporter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnalogueReporterImpl : AnalogueReporter {

    private lateinit var amplitudeReporter: AmplitudeReporter
    private lateinit var airshipReporter: AirshipReporter
    private lateinit var dataDogReporter: DataDogReporter
    private var debugMode: Boolean = true

    override fun setup(setupKeyValueMap:  HashMap<String, Any>, debugMode: Boolean ){
        this.debugMode = debugMode
        checkAmplitudeKeysBeforeInstantiating(setupKeyValueMap = setupKeyValueMap)
        checkAirshipKeysBeforeInstantiating(setupKeyValueMap = setupKeyValueMap)
        checkDataDogKeysBeforeInstantiating(setupKeyValueMap = setupKeyValueMap)
     }

    override fun report(action: AnalogueAction){

        CoroutineScope(Job()).launch(Dispatchers.Default) {

            if (debugMode) {
                Log.d("Analogue Reporter", action.toString())
            }

            if (::amplitudeReporter.isInitialized) {
                amplitudeReporter.report(analogueAction = action)
            }

            if (::airshipReporter.isInitialized) {
                airshipReporter.report(analogueAction = action)
            }

            if (::dataDogReporter.isInitialized) {
                dataDogReporter.report(analogueAction = action)
            }
        }
    }


    /**Amplitude **/
    private fun checkAmplitudeKeysBeforeInstantiating(setupKeyValueMap:  HashMap<String, Any>) {
        if(checkAmplitudeKeys(setupKeyValueMap)){
            amplitudeReporter = AmplitudeReporter()
            amplitudeReporter.setup(
                apiKey = setupKeyValueMap[AnalogueSetupKeys.Amplitude.API_KEY].toString(),
                someOtherKey = setupKeyValueMap[AnalogueSetupKeys.Amplitude.OTHER_KEY].toString()
            )
        }
    }
    private fun checkAmplitudeKeys(setupKeyValueMap: HashMap<String, Any>) =
        setupKeyValueMap.containsKey(AnalogueSetupKeys.Amplitude.API_KEY) &&
                setupKeyValueMap[AnalogueSetupKeys.Amplitude.API_KEY].toString().isNotBlank() &&
                setupKeyValueMap.containsKey(AnalogueSetupKeys.Amplitude.OTHER_KEY) &&
                setupKeyValueMap[AnalogueSetupKeys.Amplitude.OTHER_KEY].toString().isNotBlank()


    /** Airship **/
    private fun checkAirshipKeysBeforeInstantiating(setupKeyValueMap: java.util.HashMap<String, Any>) {
        if(checkAirshipKeys(setupKeyValueMap)){
            dataDogReporter = DataDogReporter()
            dataDogReporter.setup(
                apiKey = setupKeyValueMap[AnalogueSetupKeys.Airship.API_KEY].toString(),
                someOtherKey = setupKeyValueMap[AnalogueSetupKeys.Airship.OTHER_KEY].toString()
            )
        }
    }

    private fun checkAirshipKeys(setupKeyValueMap: HashMap<String, Any>): Boolean {
        return true
    }


    /** DataDog **/
    private fun checkDataDogKeysBeforeInstantiating(setupKeyValueMap: HashMap<String, Any>) {
        if(checkDataDogKeys(setupKeyValueMap)){
            dataDogReporter = DataDogReporter()
            dataDogReporter.setup(
                apiKey = setupKeyValueMap[AnalogueSetupKeys.DataDog.API_KEY].toString(),
                someOtherKey = setupKeyValueMap[AnalogueSetupKeys.DataDog.OTHER_KEY].toString()
            )
        }
    }

    private fun checkDataDogKeys(setupKeyValueMap: HashMap<String, Any>): Boolean {
        //check keys
        return false
    }



}