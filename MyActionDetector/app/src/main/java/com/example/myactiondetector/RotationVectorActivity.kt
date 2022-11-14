package com.example.myactiondetector

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.nio.channels.FileLock

class RotationVectorActivity:AppCompatActivity() {
    companion object {
        val  TAG = "SensorsTutorial"
    }
    lateinit var sensorManager: SensorManager
    lateinit var rotationVectorSensor:Sensor
    lateinit var rotationVectorSensorEventListener: MySensorEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)
        rotationVectorSensorEventListener  = MySensorEventListener()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(rotationVectorSensorEventListener,rotationVectorSensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(rotationVectorSensorEventListener)
    }
    inner class MySensorEventListener():SensorEventListener{
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val rotationMatrix = FloatArray(16)
            SensorManager.getRotationMatrixFromVector(rotationMatrix,sensorEvent.values)
            val remappedRotationMatrix = FloatArray(16)
            SensorManager.remapCoordinateSystem(rotationMatrix,SensorManager.AXIS_X,SensorManager.AXIS_Z,remappedRotationMatrix)
            val orientations = FloatArray(3)
            SensorManager.getOrientation(remappedRotationMatrix,orientations)
            for (i in 0..2){
                orientations[i] = Math.toDegrees(orientations[i].toDouble()).toFloat()
            }

            if (orientations[2] > 45){
                window.decorView.setBackgroundColor(Color.YELLOW)
            }else if (orientations[2] < -45){
                window.decorView.setBackgroundColor(Color.BLUE)
            }else if (orientations[2] < 10){
                window.decorView.setBackgroundColor(Color.WHITE)
            }
        }
    }
}