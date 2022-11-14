package com.example.myactiondetector

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class GyroscopeActivity:AppCompatActivity() {
    companion object {
        val TAG = "SensorTutorial"
    }

    lateinit var sensorManager: SensorManager
    lateinit var gyroscopeSensor: Sensor
    private lateinit var gyroscopeEventLisenter:MySensorEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        gyroscopeEventLisenter = MySensorEventListener()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(gyroscopeEventLisenter,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(gyroscopeEventLisenter)
    }
    inner class MySensorEventListener():SensorEventListener{
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            TODO("Not yet implemented")
        }

        override fun onSensorChanged(sensorEvent: SensorEvent) {
            if (sensorEvent.values[2]>0.5f) {
                window.decorView.setBackgroundColor(Color.BLUE)
            }else{
                window.decorView.setBackgroundColor(Color.YELLOW)
            }
        }
    }
}