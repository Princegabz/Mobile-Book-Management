package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

       val btnMenuAboutUs = findViewById<ImageButton>(R.id.btnMenuAboutUs)

        btnMenuAboutUs.setOnClickListener() {
            val intent = Intent(this, MenuLinks::class.java)

            startActivity(intent) // go back to main activity
        }


    }
}