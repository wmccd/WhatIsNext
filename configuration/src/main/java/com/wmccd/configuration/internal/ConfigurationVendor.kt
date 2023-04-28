package com.wmccd.configuration.internal


internal interface ConfigurationVendor {
    fun setup()
    fun shutdown()
    fun <T> fetch(key: String, default: T): T
    fun <T> observe(key: String, updateCallback: (key: String, flag: T)->Unit, default: T  )
    fun refresh()
}