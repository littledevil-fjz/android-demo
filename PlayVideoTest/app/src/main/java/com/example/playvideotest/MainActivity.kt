package com.example.playvideotest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.playvideotest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        val videoView = binding.videoView
        val play = binding.play
        val pause = binding.pause
        val replay = binding.repaly
        videoView.setVideoURI(uri)
        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val videoView = binding.videoView
        videoView.suspend()
    }
}