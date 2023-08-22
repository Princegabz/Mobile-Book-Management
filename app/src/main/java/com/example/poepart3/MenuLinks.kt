package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MenuLinks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_links)
        val lstViewMenu = findViewById<ListView>(R.id.lstViewMenu)

        val Genre = arrayListOf("Categories","About Us", "Logout")
        val adapter  = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Genre) //(pervais,2018)

        lstViewMenu.adapter=adapter

        lstViewMenu.setOnItemClickListener { parent, view, position, id -> //(pervais,2018)
            if (position==0) {
                val it = Intent(this, categories::class.java)
                it.putExtra("Category1", ""+ Genre[position])
                startActivity(it)
            }
            if (position==1) {
                val it = Intent(this, AboutUs::class.java)
                it.putExtra("Category2", ""+ Genre[position])
                startActivity(it)
            }
            if (position==2) {
                val it = Intent(this, SignIn::class.java)
                it.putExtra("Category2", ""+ Genre[position])
                startActivity(it)
            }
        }
    }
}