package com.wmccd.whatisnext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.books.BookScreen
import com.wmccd.home.external.HomeScreen
import com.wmccd.records.external.RecordScreen
import com.wmccd.weather.WeatherScreen
import com.wmccd.whatisnext.MyApp.Companion.appAnalogueReporter
import com.wmccd.whatisnext.injection.HomeDependencyGenerator
import com.wmccd.whatisnext.navigation.NavBarItems
import com.wmccd.whatisnext.navigation.NavRoutes
import com.wmccd.whatisnext.ui.theme.WhatIsNextTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatIsNextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appAnalogueReporter.setup(hashMapOf(), debugMode = true)
                    appAnalogueReporter.report(action = AnalogueAction.Event("XXX"))
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar (
                title = {Text("What is next?")}
            )
        },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ){
        composable(NavRoutes.Home.route)  {
            HomeScreen(
                counterViewModel = HomeDependencyGenerator().generate()
            )
        }
        composable(NavRoutes.Records.route)  {
            RecordScreen(
                //viewModel = injection.recordCollectionViewModel
            )
        }
        composable(NavRoutes.Books.route)  {
            BookScreen(
                //viewModel = injection.recordCollectionViewModel
            )
        }
        composable(NavRoutes.Weather.route)  {
            WeatherScreen(
                //viewModel = injection.recordCollectionViewModel
            )
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(
                        route = navItem.route
                    ){
                        popUpTo( id = navController.graph.findStartDestination().id){
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.imageId) ,
                        contentDescription = navItem.title,
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = navItem.title,
                        color = Color.White
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhatIsNextTheme {
        MainScreen()
    }
}