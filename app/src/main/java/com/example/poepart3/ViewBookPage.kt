package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class ViewBookPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book_page)

        val btnUpdateBookView = findViewById<Button>(R.id.btnUpdateViewDetails)
        val txtTitle  = findViewById<TextView>(R.id.txtViewTitle)
        val txtAquisition  = findViewById<TextView>(R.id.txtViewDate2)
        val txtAuthor = findViewById<TextView>(R.id.txtViewAuthor)
        val txtPrice = findViewById<TextView>(R.id.txtViewPrice)
        val txtSeries = findViewById<TextView>(R.id.txtViewSeries)
        val txtRating = findViewById<TextView>(R.id.txtViewScore)
        val txtRead = findViewById<CheckBox>(R.id.checkBoxRead)
        val txtFav = findViewById<CheckBox>(R.id.checkBoxFav)



        val ID = intent.getStringExtra("ID")
        val bookID = intent.getStringExtra("takeBookID")
        val bookName = intent.getStringExtra("takeBookName")
        val bookTitle = intent.getStringExtra("takecateTitle")



        var boolRead: Boolean = false
        var boolFav: Boolean = false

        val passedCatID = ID?.toInt()
        val passedbookID = bookID?.toInt()

        var HelpClass = HelperClass()
        var arrBook = HelpClass.getBooksArray()

        var image : Int = 0

        for(i in 0..arrBook.size-1 ) {
            if (arrBook[i].CategoryID == passedCatID && arrBook[i].bookid == passedbookID && arrBook[i].Title.equals(bookName)) {

                txtTitle.text = arrBook[i].Title
                txtAquisition.text = arrBook[i].AquisDate
                txtAuthor.text = arrBook[i].Author
                txtPrice.text = ""+arrBook[i].Price
                txtSeries.text = arrBook[i].Series
                txtRating.text = ""+arrBook[i].Rating
                image = arrBook[i].BookImage
                txtRead.tag = arrBook[i].Read
                txtRead.isChecked  = arrBook[i].Read

                txtFav.tag = arrBook[i].Favourite
                txtFav.isChecked = arrBook[i].Favourite



            }
        }

        txtRead.setOnClickListener{
            boolFav = txtRead.isChecked
            boolRead = txtFav.isChecked

        }
        txtFav.setOnClickListener{
            boolFav = txtRead.isChecked
            boolRead = txtFav.isChecked
        }


        btnUpdateBookView.setOnClickListener{
            val convertedBookID = HelpClass.convertToInt(bookID.toString())
            val convertedID = HelpClass.convertToInt(ID.toString())
            val convertedPrice = HelpClass.convertToInt(txtPrice.text.toString())
            val convertedRating = HelpClass.convertToInt(txtRating.text.toString())
            HelpClass.updateBook(convertedBookID, txtTitle.text.toString(), txtAquisition.text.toString(), txtAuthor.text.toString(),convertedPrice,
                txtSeries.text.toString(),convertedRating,image, convertedID, boolFav,boolRead)

            val intent = Intent(this, categories::class.java)
            //passing the name and id back to books page
            intent.putExtra("ID", convertedID)
            intent.putExtra("Name", bookTitle)
            startActivity(intent)
        }


    }
}