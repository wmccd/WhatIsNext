package com.wmccd.configuration.internal.lanuch_darkly

import com.wmccd.configuration.internal.ConfigurationVendor

internal class LaunchDarkly: ConfigurationVendor {
    override fun setup() {}
    override fun shutdown() {}
    override fun <T> fetch(key: String, default: T): T {
        return fakingLaunchDarkly(key = key) as T }
    override fun <T> observe(
        key: String,
        updateCallback: (key: String, flag: T) -> Unit,
        default: T
    ) {}
    override fun refresh() {}

    //-----------------
    private fun fakingLaunchDarkly(key: String): String{
        return when(key){
            "homeTabLabel" -> "Home"
            "recordTabLabel" -> "Records"
            "bookTabLabel" -> "Books"
            "weatherTabLabel" -> "Weather"
            "loadingMessage" -> "Loading..."
            "erroringMessage" -> "Uh-oh...something went wrong"

            "homeScreenTitle" -> "Counter of Things"
            "bookCountLabel" -> "Book count: "
            "recordCountLabel" -> "Record count: "


            else -> ""
        }
    }


}