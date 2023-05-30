package com.example.sportevents.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportevents.databinding.ActivityMainBinding
import com.example.sportevents.screens.sport.SportFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sportFragment = SportFragment()
        supportFragmentManager.beginTransaction().add(binding.fcMain.id, sportFragment).commit()
    }
}