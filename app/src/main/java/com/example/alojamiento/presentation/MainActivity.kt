package com.example.alojamiento.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alojamiento.R
import com.example.alojamiento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private lateinit var binding: ActivityMainBinding
    private fun setupBinding(){
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}