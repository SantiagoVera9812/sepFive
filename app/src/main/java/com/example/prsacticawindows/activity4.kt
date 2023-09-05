package com.example.prsacticawindows

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prsacticawindows.databinding.Activity4Binding

class activity4 : AppCompatActivity() {
    private lateinit var binding : Activity4Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun loadImage(uri: Uri){

        val imageStream = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(imageStream)

    }
}