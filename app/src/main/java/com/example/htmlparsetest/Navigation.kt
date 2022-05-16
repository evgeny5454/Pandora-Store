package com.example.htmlparsetest

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.htmlparsetest.screens.DetailsScreen
import com.example.htmlparsetest.screens.MainScreen
import com.example.htmlparsetest.view_model.MainViewModel

const val END_POINT = "end_point"
//const val PRISE = "prise"

/*
@Composable
fun Navigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{$END_POINT}",
            arguments = listOf(
                navArgument(END_POINT) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            DetailsScreen(
                mainViewModel = mainViewModel,
                endPoint = entry.arguments?.getString(END_POINT), navController = navController
            )
        }
    }
}
*/

