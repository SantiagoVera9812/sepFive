package com.example.prsacticawindows

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.prsacticawindows.databinding.ActivityFinalmechiBinding

class finalmechi : AppCompatActivity() {

    private lateinit var binding: ActivityFinalmechiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinalmechiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val string = intent.getStringExtra("campoNuevo")
        val string2 = intent.getStringExtra("campoNuevo2")
        val string3 = intent.getStringExtra("idioma")
        val string4 = intent.getStringExtra("saludo")
        val string5 = intent.getStringExtra("date")
        val string6 = intent.getStringExtra("category")

        binding.nombreCapital.text = string
        binding.siglas.text = string2
        binding.idioma.text = string3
        binding.saludos.text = string4
        binding.date.text = string5
        binding.category.text = string6



    }

}