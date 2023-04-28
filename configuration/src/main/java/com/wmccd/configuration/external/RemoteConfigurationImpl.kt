package com.wmccd.configuration.external

import com.wmccd.configuration.internal.ConfigurationVendor
import com.wmccd.configuration.internal.lanuch_darkly.LaunchDarkly

class RemoteConfigurationImpl: RemoteConfiguration {

    private val configurationVendor: ConfigurationVendor = LaunchDarkly()

    override fun setup(setupKeyValueMap: HashMap<String, Any>, debugMode: Boolean) {}

    override fun <T> fetch(key: String, default: T): T {
        return configurationVendor.fetch(key, default)
    }

    override fun <T> observe(
        key: String,
        updateCallback: (key: String, flag: T) -> Unit,
        default: T
    ) {
        TODO("Not yet implemented")
    }
}