package com.yoshixmk.sensors.ui.screen

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yoshixmk.sensors.ui.screen.dashboard.DashboardScreen
import com.yoshixmk.sensors.ui.screen.splash.SplashScreen
import com.yoshixmk.sensors.ui.theme.sensorsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            sensorsTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Composable
    private fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Splash.route) {

            // Splash
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        val options = NavOptions.Builder()
                            .setPopUpTo(Screen.Splash.route, inclusive = true)
                            .build()
                        navController.navigate(
                            Screen.Dashboard.route,
                            options
                        ) // Move to dashboard
                    }
                )
            }

            // Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen(sensorManager)
            }
        }
    }
}
