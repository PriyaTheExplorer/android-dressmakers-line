package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            startActivity(Intent(this,UploadActivity::class.java))
            finish()
        }

        binding.mainUpdate.setOnClickListener {
            startActivity(Intent(this,UpdateActivity::class.java))
            finish()
        }

        binding.mainDelete.setOnClickListener {
            startActivity(Intent(this,DeleteActivity::class.java))
            finish()
        }

        binding.mainRead.setOnClickListener {
            startActivity(Intent(this,ReadActivity::class.java))
            finish()
        }

    }
}