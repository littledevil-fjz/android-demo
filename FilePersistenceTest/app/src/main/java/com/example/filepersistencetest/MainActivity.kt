package com.example.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.filepersistencetest.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取绑定类实例
        binding = ActivityMainBinding.inflate(layoutInflater)
        //通过绑定类获取布局后，传给setContentView设置内容视图
        setContentView(binding.root)
        val inputText = load()
        if (inputText.isNotBlank()){
            binding.editText.setText(inputText)
            binding.editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
//        val inputText = binding.editText.text.toString()
//        save(inputText)
        super.onDestroy()
        val inputText = binding.editText.text.toString()
        save(inputText)
    }
    private fun save(inputText:String){
        try {
            val outPut = openFileOutput("data", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outPut))
            writer.use {
                it.write(inputText)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun load():String{
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}