package com.example.htmlparsetest

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.htmlparsetest.navigation.NavigationTrue
import com.example.htmlparsetest.view_model.MainViewModel
import com.example.htmlparsetest.navigatoin_drawer.Drawer
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
lateinit var activity: MainActivity

class MainActivity : ComponentActivity() {

    private val MY_PERMISSIONS_REQUEST = 1234
    private val PERMISSIONS = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        setContent {
            MainScreen()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isPermissions()) {
            requestPermissions(PERMISSIONS, MY_PERMISSIONS_REQUEST)
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST && grantResults.isNotEmpty()) {
            if (isPermissions()) {
                (Objects.requireNonNull(this.getSystemService(Context.ACTIVITY_SERVICE)) as ActivityManager).clearApplicationUserData()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isPermissions(): Boolean {
        PERMISSIONS.forEach {
            if (checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null) return
        val uri = data.data ?: return
        val inputStream = contentResolver.openInputStream(uri)
        val reader = BufferedReader(InputStreamReader(inputStream))
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        scaffoldState = scaffoldState,
        /*topBar = { TopBar(scope = scope, scaffoldState = scaffoldState)},*/
        drawerBackgroundColor = Color.LightGray,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController )
        },
    ) {
        NavigationTrue(navController = navController, mainViewModel = mainViewModel,scope = scope, scaffoldState = scaffoldState)
    }
}
