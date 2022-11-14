package com.example.myactiondetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openProximity(view: View){
        startActivity(Intent(this,ProximityActivity::class.java))
    }

    fun openGyroscope(view: View){
        startActivity(Intent(this,GyroscopeActivity::class.java))
    }

    fun openRotationVector(view: View){
        startActivity(Intent(this,RotationVectorActivity::class.java))
    }
}