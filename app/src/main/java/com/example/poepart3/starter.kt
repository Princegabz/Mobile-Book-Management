package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class starter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        val btnShowedBronze = findViewById<Button>(R.id.btnBronzeBack)

        btnShowedBronze.setOnClickListener{
            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }
    }
}