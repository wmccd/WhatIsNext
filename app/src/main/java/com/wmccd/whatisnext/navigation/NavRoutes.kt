package com.wmccd.whatisnext.navigation

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Records: NavRoutes("records")
    object Books: NavRoutes("books")
    object Weather: NavRoutes("weather")
}
