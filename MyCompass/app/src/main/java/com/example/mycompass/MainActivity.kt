package com.example.mycompass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var sensor:Sensor
    lateinit var sensorManager:SensorManager
    lateinit var compassImage :ImageView
    var DegreeStart = 0f
    lateinit  var DegreeTV:TextView
    lateinit var eventListener: MySensorEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compassImage = findViewById(R.id.compass_image)
        DegreeTV = findViewById(R.id.DegreeTV)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        eventListener = MySensorEventListener()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(eventListener)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(eventListener,sensor,SensorManager.SENSOR_DELAY_GAME)
    }

    inner class MySensorEventListener:SensorEventListener{
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }

        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val degree = Math.round(sensorEvent.values[0]).toFloat()
            DegreeTV.setText("Heading: "+degree.toString()+" degree")
            val ra = RotateAnimation(DegreeStart,-degree, Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F)
            ra.fillAfter = true
            ra.duration = 210
            compassImage.startAnimation(ra)
            DegreeStart = -degree
        }
    }
}