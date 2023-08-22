package com.example.poepart3
/*
  reference list
  mullatoez - github - 28 Nov 2021 - https://github.com/mullatoez/pickImageFromGallery/tree/main/app/src/main/java/com/example/imgstut
*/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.poepart3.databinding.ActivityAddBookPageBinding


class AddBookPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookPageBinding //(mullatoez, 2021)


    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookPageBinding.inflate(layoutInflater)

        //setting the image in the imageview
        setContentView(binding.root)
        binding.imviewAddBook.setOnClickListener {
            pickImageFromGallery() //(mullatoez, 2021)
        }
        //setContentView(R.layout.activity_add_book_page)

        val btnAddBook = findViewById<Button>(R.id.btnAddBook)
        val txtTitle  = findViewById<EditText>(R.id.txtAddTitle)
        val txtAquisition  = findViewById<EditText>(R.id.txtAddDatePur)
        val txtAuthor = findViewById<EditText>(R.id.txtAddAuthor)
        val txtPrice = findViewById<EditText>(R.id.txtAddPrice)
        val txtSeries = findViewById<EditText>(R.id.txtAddSeries)
        val txtRating = findViewById<EditText>(R.id.txtAddReviewRating)

        var help = HelperClass()

        //getting the category ID
        var CatID = intent.getStringExtra("takecateID")
        //tekecateTitle
        var CatName = intent.getStringExtra("takecateTitle")


        var trupassed: Int = 0
        val passedID = CatID?.toInt()
        if (passedID != null){
            trupassed = passedID
        }


        btnAddBook.setOnClickListener {

            var convertedPrice : Int = 0
            var convertedRating : Int = 0

            convertedPrice = help.convertToInt(txtPrice.text.toString())//converting to Int
            convertedRating = help.convertToInt(txtRating.text.toString())

            //adding the book to arraylist with categoryID as foreign key
            help.AddBook(help.getBookLength()+1, txtTitle.text.toString(), txtAquisition.text.toString(),
                txtAuthor.text.toString(),convertedPrice , txtSeries.text.toString(), convertedRating,R.drawable.imgplaceholder2,trupassed, false, false)


            //where checked for award if it should be shown



            //going to category books page
            val intent = Intent(this, CategoryBooksPage::class.java)
            //passing the name and id back to books page
            intent.putExtra("ID", CatID)
            intent.putExtra("Name", CatName)
            startActivity(intent)
        }

    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE) //(mullatoez, 2021)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imviewAddBook.setImageURI(data?.data) //(mullatoez, 2021)
        }
    }
}

