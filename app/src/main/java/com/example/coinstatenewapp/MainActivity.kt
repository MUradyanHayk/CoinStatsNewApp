package com.example.coinstatenewapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coinstatenewapp.databinding.ActivityMainBinding
import com.example.coinstatenewapp.utils.ApplicationManager.MAIN

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MAIN = this
    }
}