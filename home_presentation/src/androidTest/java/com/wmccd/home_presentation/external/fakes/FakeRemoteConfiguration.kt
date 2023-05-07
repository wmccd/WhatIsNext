package com.wmccd.home_presentation.external.fakes

import com.wmccd.configuration.external.RemoteConfiguration
import org.hamcrest.core.AnyOf

class FakeRemoteConfiguration(): RemoteConfiguration {
    override fun setup(setupKeyValueMap: HashMap<String, Any>, debugMode: Boolean) {}
    override fun <T> fetch(key: String, default: T): T { return default }
    override fun <T> observe(
        key: String,
        updateCallback: (key: String, flag: T) -> Unit,
        default: T
    ) {}
}