package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DeleteCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_category)

        val btnDeleteCat = findViewById<Button>(R.id.btnYesDelete)
        val btnNotDeleteCat= findViewById<Button>(R.id.btnAddBook)

        val ID = intent.getStringExtra("ID")
        var trupassed: Int = 0
        val passedID = ID?.toInt()
        if (passedID != null){
            trupassed = passedID

        }


        var help = HelperClass()

        btnNotDeleteCat.setOnClickListener{
            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }

        btnDeleteCat.setOnClickListener{
            help.DeleteCategory(trupassed)
            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }
    }
}