package com.example.servicetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicetest.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDialogBinding
    companion object {
        val TAG = "DialogActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }
    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }
}