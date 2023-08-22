package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class packrat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packrat)


        val btnShowedGold = findViewById<Button>(R.id.btnGoldBack)

        btnShowedGold.setOnClickListener{
            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }
    }
}