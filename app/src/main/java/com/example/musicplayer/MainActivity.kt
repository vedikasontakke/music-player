package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.media.MediaPlayer
import android.view.View

class MainActivity : AppCompatActivity() {
    // Instantiating the MediaPlayer class
    var music: MediaPlayer? = null
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding the music file to our
        // newly created object music
        music = MediaPlayer.create(this, R.raw.aeroplane)
    }

    // Plaing the music
    fun musicplay(v: View?) {
        music?.start()
    }

    // Pausing the music
    fun musicpause(v: View?) {
        music?.pause()
    }

    // Stoping the music
    fun musicstop(v: View?) {
        music?.stop()
        music = MediaPlayer.create(
                this, R.raw.aeroplane)
    }
}