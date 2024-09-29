package com.binayshaw7777.addsubz.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binayshaw7777.addsubz.presentation.screens.home_screen.HomeScreen
import com.binayshaw7777.addsubz.presentation.screens.setting_screen.SettingScreen
import com.binayshaw7777.addsubz.presentation.screens.shopping_calculator.ShoppingCalculator
import com.binayshaw7777.addsubz.presentation.theme.FadeIn
import com.binayshaw7777.addsubz.presentation.theme.FadeOut
import com.binayshaw7777.addsubz.utils.compose_utils.BackPressCompose

val LocalNavHost = staticCompositionLocalOf<NavHostController> {
    error("No Parameter is available")
}

@SuppressLint("ComposableDestinationInComposeScope", "ComposableNavGraphInComposeScope")
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    BackPressCompose()

    CompositionLocalProvider(LocalNavHost provides navController) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            modifier = Modifier.then(modifier)
        ) {
            NavHost(
                navController,
                startDestination = Screens.HomeScreen.name,
                modifier = Modifier.padding(it),
                enterTransition = { FadeIn },
                exitTransition = { FadeOut },
                popEnterTransition = { FadeIn },
                popExitTransition = { FadeOut },
            ) {

                composable(route = Screens.HomeScreen.name) {
                    HomeScreen()
                }
                composable(route = Screens.SettingScreen.name) {
                    SettingScreen()
                }
                composable(route = Screens.ShoppingCalculatorScreen.name) {
                    ShoppingCalculator(viewModel = hiltViewModel())
                }
            }
        }
    }
}