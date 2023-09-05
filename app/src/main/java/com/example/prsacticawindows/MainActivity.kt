package com.example.prsacticawindows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prsacticawindows.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{

            startActivity(Intent(this, newActivity::class.java))
        }

        binding.imageButton5.setOnClickListener{

           startActivity(Intent(this, finalmechi::class.java))
        }

        binding.imageButton6.setOnClickListener {

            startActivity(Intent(this, iop::class.java))
        }

    }




}