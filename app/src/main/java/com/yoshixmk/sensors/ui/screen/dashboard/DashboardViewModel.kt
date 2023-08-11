package com.yoshixmk.sensors.ui.screen.dashboard

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    private val _sensors = MutableStateFlow(emptyList<Sensor>())
    val sensors: StateFlow<List<Sensor>> = _sensors

    fun onClickMeClicked(sensorManager: SensorManager) {
        _sensors.value = sensorManager.getSensorList(Sensor.TYPE_ALL)
    }
}