package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryBooksPage : AppCompatActivity() {
    lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_books_page)
        val toAddBook = findViewById<ImageButton>(R.id.imgAddBook)
        val backTocategories = findViewById<ImageButton>(R.id.imgButtonBackCate)
        val header = findViewById<TextView>(R.id.booksHeader)
        val bfeed : RecyclerView = findViewById(R.id.BFeed)
        var HelpClass = HelperClass()


        bookAdapter= BookAdapter() // calling class
        bfeed.apply {
            layoutManager = LinearLayoutManager(this@CategoryBooksPage)
            adapter = bookAdapter
        }

        var arrBook = HelpClass.getBooksArray()
        val Items = mutableListOf<Book>()

        val ID = intent.getStringExtra("ID")
        val categoryName = intent.getStringExtra("Name")
        val passedCatID = ID?.toInt()
        if (passedCatID != null){

        }
        //setting header
        header.text = "${categoryName} Books"
        //adding the books of the category to the list
        for(i in 0..arrBook.size-1 ) {
            if (arrBook[i].CategoryID == passedCatID) {
                Items.add(
                    Book( // calling data class
                        bookid = arrBook[i].bookid,
                        Title = arrBook[i].Title,
                        Series = arrBook[i].Series,
                        BookImage = arrBook[i].BookImage,
                        CategoryID = arrBook[i].CategoryID
                    )
                )
            }
        }
        bookAdapter.submitList(Items)

        //goes to the add book page
        toAddBook.setOnClickListener() {
            val intent = Intent(this, AddBookPage::class.java)
            intent.putExtra("takecateID", ID)
            intent.putExtra("takecateTitle", categoryName)
            startActivity(intent) // go back to main activity
        }

        //check for reward
        var len = HelpClass.getBookLength()
        var arrawards = HelpClass.getBooksArrray()
        if(len == 1 && arrawards[0].starter == false)
        {
            HelpClass.UpdateAwardsUser(true, false, false)
            val intent = Intent(this, starter::class.java)
            startActivity(intent)
        }
        else if(len == 3 && arrawards[0].collector == false){
            HelpClass.UpdateAwardsUser(true, true, false)
            val intent = Intent(this, collector::class.java)
            startActivity(intent)

        }
        else if(len == 10 && arrawards[0].packrat == false){
            HelpClass.UpdateAwardsUser(true, true, true)
            val intent = Intent(this, packrat::class.java)
            startActivity(intent)
        }

        bookAdapter.onItemClickB= {
            val intent = Intent(this, ViewBookPage::class.java)
            val passBookID = it.bookid.toString()
            val passBookTitle = it.Title.toString()
            intent.putExtra("ID", ID)
            intent.putExtra("takeBookID", passBookID)
            intent.putExtra("takeBookName", passBookTitle)
            intent.putExtra("takecateTitle", categoryName)

            startActivity(intent) // go back to main activity
        }
        //goes back to categories page
        backTocategories.setOnClickListener() {
            val intent = Intent(this, categories::class.java)

            startActivity(intent) // go back to main activity
        }
    }
}