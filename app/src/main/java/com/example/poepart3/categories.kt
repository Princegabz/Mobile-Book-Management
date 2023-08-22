package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class categories : AppCompatActivity() {
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        val imgToAddAvatar = findViewById<ImageButton>(R.id.imgAddCategory)
        val menu = findViewById<ImageButton>(R.id.btnMenu)

        //imgAddCategory
        val feed : RecyclerView = findViewById(R.id.Feed)
        userAdapter= UserAdapter() // calling class
        feed.apply {
            layoutManager = LinearLayoutManager(this@categories)
            adapter = userAdapter
        }

        var HelpClass = HelperClass()
        var arr = HelpClass.getCategoriesArray()
        val Items = mutableListOf<User>()

        var goalmsg: String
        for(i in 0..arr.size-1 ) {

            var NumOfBooks:Int = 0
            if(arr[i].CatGoal == 0){
                goalmsg = "No goal set"
            }
            else{
                NumOfBooks = HelpClass.getNumBooksOfCategory(i+1)
                goalmsg = "${NumOfBooks} / "+arr[i].CatGoal

            }
            Items.add(
                User( // calling data class
                    catid = arr[i].CatID,
                    Name = arr[i].CatName,
                    currentprogress = NumOfBooks,
                    maxgoal = arr[i].CatGoal,
                    goalmsg = goalmsg,
                    imageURL = arr[i].CatImage
                )
            )
        }
        userAdapter.submitList(Items)
        //goes to category books
        userAdapter.onItemClick = {
            val intent = Intent(this,CategoryBooksPage::class.java)
            val passID = it.catid.toString()
            intent.putExtra("ID", passID)
            intent.putExtra("Name", it.Name)
            val send = 1
            startActivity(intent)

        }

        userAdapter.UpdateCategoryPage = {
            val intent = Intent(this,UpdateCategoryPage::class.java)
            val passID = it.catid.toString()
            intent.putExtra("ID", passID)
            val send = 1
            startActivity(intent)

        }

        userAdapter.DeleteCategoryPage = {
            val intent = Intent(this,DeleteCategory::class.java)
            val passID = it.catid.toString()
            intent.putExtra("ID", passID)
            startActivity(intent)

        }
        //goes to add category page
        imgToAddAvatar.setOnClickListener() {
            val intent = Intent(this, AddCategoryPage::class.java)
            startActivity(intent) // go back to main activity
        }
        menu.setOnClickListener() {
            val intent = Intent(this, MenuLinks::class.java)
            startActivity(intent) // go back to main activity
        }
    }
}