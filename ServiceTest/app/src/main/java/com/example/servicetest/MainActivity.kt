package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.servicetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var startNormalActivity = binding.startNormalActivity
        var startDialogActivity = binding.startDialogActivity

        startNormalActivity.setOnClickListener {
            val intent = Intent(this@MainActivity,NormalActivity::class.java)
            startActivity(intent)
        }

        startDialogActivity.setOnClickListener {
            val intent = Intent(this@MainActivity,DialogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }
    
    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}