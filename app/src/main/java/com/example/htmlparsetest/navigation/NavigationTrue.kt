package com.example.htmlparsetest.navigation

import android.os.Environment
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.htmlparsetest.Screen
import com.example.htmlparsetest.navigatoin_drawer.NavDrawerItem
import com.example.htmlparsetest.screens.CarAlarmsView
import com.example.htmlparsetest.screens.DetailsScreen
import com.example.htmlparsetest.screens.FileManagerView
import com.example.htmlparsetest.screens.ProfileView
import com.example.htmlparsetest.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope

const val END_POINT = "end_point"

@Composable
fun NavigationTrue(navController: NavHostController, mainViewModel: MainViewModel, scope: CoroutineScope, scaffoldState: ScaffoldState) {

    var files = Environment.getExternalStorageDirectory()

    NavHost(navController = navController, startDestination = NavDrawerItem.CarAlarms.route) {
        composable(NavDrawerItem.CarAlarms.route) {
            CarAlarmsView(mainViewModel = mainViewModel, navController = navController, scope = scope , scaffoldState = scaffoldState)
        }
        composable(NavDrawerItem.Profile.route) {
            ProfileView(navController = navController)
        }
        composable(route = Screen.FileManagerView.route) {
            FileManagerView(navController = navController, files = files , onClick = {
                files = Environment.getExternalStoragePublicDirectory(it.absolutePath)
            })
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