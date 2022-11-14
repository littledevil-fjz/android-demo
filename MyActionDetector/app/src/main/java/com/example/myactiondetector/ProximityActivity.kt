package com.example.myactiondetector

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ProximityActivity: AppCompatActivity() {
    companion object {
        val TAG = "SensorsTutorial"
    }
    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var proximitySensorEventListener: SensorEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (proximitySensor == null)  {
            Log.d(TAG, "onCreate: Proximity sensor not available.")
            finish()
        }
        proximitySensorEventListener = MySensorEventListener()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(proximitySensorEventListener,proximitySensor,2*1000*1000)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(proximitySensorEventListener)
    }
    inner class MySensorEventListener:SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

        override fun onSensorChanged(sensorEvent: SensorEvent) {
            if (sensorEvent.values[0]<proximitySensor.maximumRange){
                    window.decorView.setBackgroundColor(Color.RED)
            }else{
                window.decorView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}