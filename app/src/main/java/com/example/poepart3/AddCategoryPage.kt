package com.example.poepart3

/*
  reference list
  mullatoez - github - 28 Nov 2021 - https://github.com/mullatoez/pickImageFromGallery/tree/main/app/src/main/java/com/example/imgstut
*/
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.poepart3.databinding.ActivityAddCategoryPageBinding

class AddCategoryPage : AppCompatActivity() {
    private lateinit var binding: ActivityAddCategoryPageBinding

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryPageBinding.inflate(layoutInflater) //(mullatoez, 2021)

        setContentView(binding.root)

        var help = HelperClass()

        val btnAddCat = findViewById<Button>(R.id.btnAddCategory)
        val txtName = findViewById<EditText>(R.id.txtAddCatName)
        val txtGoal = findViewById<EditText>(R.id.txtAddCatGoal)
        val cateImage = findViewById<ImageView>(R.id.imviewAddCate)

        binding.imviewAddCate.setOnClickListener {
            pickImageFromGallery()
            //setContentView(R.layout.activity_main)
        }
        //setContentView(R.layout.activity_add_category_page)

        //btnAddCat.setBackgroundColor(Color.parseColor("#FFFF0000"))



        btnAddCat.setOnClickListener{
            var convert = txtGoal.text.toString()

            var tempgoal = convert.toInt()
            help.AddCategory(help.getCateLength()+1, txtName.text.toString(), tempgoal, R.drawable.imgdefaultgenre)

            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
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
            binding.imviewAddCate.setImageURI(data?.data) //(mullatoez, 2021)
        }
    }

}