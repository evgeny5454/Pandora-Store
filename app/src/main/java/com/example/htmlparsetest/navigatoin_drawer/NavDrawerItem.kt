package com.example.htmlparsetest.navigatoin_drawer

import com.example.htmlparsetest.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String){
    object CarAlarms: NavDrawerItem("car_alarms", R.drawable.ic_auto, "Car Alarms")
    object CartView: NavDrawerItem("cart_view", R.drawable.ic_shopping_cart, "Cart")
    object Аccessories: NavDrawerItem("accessories", R.drawable.ic_accessories, "Accessories")
    object Profile: NavDrawerItem("profile", R.drawable.ic_profile, "Profile")
}
