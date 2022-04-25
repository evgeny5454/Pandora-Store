package com.example.htmlparsetest

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    //object DetScreen : Screen("det_screen")
    object DetailsScreen: Screen("details_screen")
    //object CartDetailScreen: Screen("cart_detail_screen")


    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
