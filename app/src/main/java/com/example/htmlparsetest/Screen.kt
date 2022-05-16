package com.example.htmlparsetest

sealed class Screen(val route: String){
    object FileManagerView : Screen("file_manager_view")
    object DetailsScreen: Screen("details_screen")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
