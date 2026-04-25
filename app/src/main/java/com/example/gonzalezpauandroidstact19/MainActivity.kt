package com.example.gonzalezpauandroidstact19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExercise1 = findViewById<Button>(R.id.button1);

        btnExercise1.setOnClickListener {
            val intent = Intent(this, Exercici1::class.java)
            startActivity(intent)

        }
        val btnExercise12 = findViewById<Button>(R.id.button2);

        btnExercise12.setOnClickListener{
            val intent = Intent( this, Exercici2::class.java)
            startActivity(intent)
        }
    }
}