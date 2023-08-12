package com.yoshixmk.sensors.ui.screen.dashboard

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
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

    private val _checking = MutableStateFlow<Sensor?>(null)
    private val checking: StateFlow<Sensor?> = _checking
    fun onClickSensor(sensorManager: SensorManager, sensor: Sensor) {
        if (checking.value != null) {
            sensorManager.unregisterListener(callback, checking.value)
        }
        if (sensorManager.registerListener(callback, sensor, sensor.type)) {
            _checking.value = sensor
        }
    }

    private val _checkingValues = MutableStateFlow(emptyArray<Float>())
    val checkingValues: StateFlow<Array<Float>> = _checkingValues
    private val callback = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            val values = event?.values ?: FloatArray(0)
            _checkingValues.value = values.toTypedArray()
            // val sensorName = event?.sensor?.stringType ?: "unknown"
            // Log.d(sensorName.uppercase(), "$sensorName Value Changed: ${value ?: -1f}")
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // val sensorName = sensor?.stringType ?: "unknown"
            // Log.d(sensorName.uppercase(), "$sensorName Accuracy Changed: $accuracy")
        }
    }
}
