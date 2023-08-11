package com.yoshixmk.sensors.ui.screen.dashboard

import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yoshixmk.sensors.R

@Composable
fun DashboardScreen(
    sensorManager: SensorManager,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sensors by viewModel.sensors.collectAsState()
        sensors.map {
            Text(text = it.name)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Action : Click Me
        Button(onClick = {
            viewModel.onClickMeClicked(sensorManager)
        }) {
            Text(text = stringResource(id = R.string.action_click_me))
        }
    }
}