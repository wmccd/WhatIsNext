package com.wmccd.configuration.external

interface RemoteConfiguration {
    fun setup(setupKeyValueMap:  HashMap<String, Any>, debugMode: Boolean)
    fun <T> fetch(key: String, default: T): T
    fun <T> observe(key: String, updateCallback: (key: String, flag: T)->Unit, default: T  )
}
