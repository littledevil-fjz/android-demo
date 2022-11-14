package com.example.androidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.androidthreadtest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val updateText = 1
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText -> binding.textView.text = "Nice to meet you"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val changeTextBtn = binding.changeTextbtn
        changeTextBtn.setOnClickListener {
            thread {
               val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
       }
    }