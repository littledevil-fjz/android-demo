package com.example.sensorlist

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity(),SensorEventListener{

    lateinit var adapter:SensorAdapter
    lateinit var sensorList: List<MySensor>
    lateinit var testview:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorList = ArrayList<MySensor>()
        sensorList = getSensors()
        adapter = SensorAdapter(this,R.layout.list_item,sensorList)
        testview = findViewById(R.id.test)

    }
    fun getSensors():List<MySensor> {
        val list : MutableList<MySensor> = ArrayList<MySensor>()
        val sm :SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val phoneSensor:List<Sensor> = sm.getSensorList(Sensor.TYPE_ALL)

        val it:Iterator<Sensor> = phoneSensor.iterator()
        while (it.hasNext()){
            val s:Sensor = it.next()
            list.add(MySensor(s,applicationContext))
        }
        return  list
    }
    override fun onSensorChanged(p0: SensorEvent?) {
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}