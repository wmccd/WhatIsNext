package com.wmccd.whatisnext.navigation

import com.wmccd.configuration_keys.external.AppConfigurationKeys
import com.wmccd.whatisnext.MyApp
import com.wmccd.whatisnext.R

object HomeBarItem {
    val item = BarItem(
        title = MyApp.remoteConfiguration.fetch(AppConfigurationKeys.Tabs.homeTabLabel, ""),
        imageId = R.drawable.ic_home,
        route = NavRoutes.Home.route
    )
}

object RecordBarItem {
    val item = BarItem(
        title = MyApp.remoteConfiguration.fetch(AppConfigurationKeys.Tabs.recordTabLabel, ""),
        imageId =  R.drawable.ic_album,
        route = NavRoutes.Records.route
    )
}

object BookBarItem {
    val item = BarItem(
        title = MyApp.remoteConfiguration.fetch(AppConfigurationKeys.Tabs.bookTabLabel, ""),
        imageId = R.drawable.ic_book,
        route = NavRoutes.Books.route
    )
}

object WeatherBarItem{
    val item = BarItem(
        title = MyApp.remoteConfiguration.fetch(AppConfigurationKeys.Tabs.weatherTabLabel, ""),
        imageId = R.drawable.ic_weather,
        route = NavRoutes.Weather.route
    )
}

object NavBarItems {
    val BarItems = listOf(
        HomeBarItem.item,
        RecordBarItem.item,
        BookBarItem.item,
        WeatherBarItem.item
    )
}