package com.example.poepart3
/*
  reference list
  Kyay10  - kotlinlang - January 2021  - https://discuss.kotlinlang.org/t/unable-to-set-edittexts-text-property-using-a-string/20630

  mullatoez - github - 28 Nov 2021 - https://github.com/mullatoez/pickImageFromGallery/tree/main/app/src/main/java/com/example/imgstut
*/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.poepart3.databinding.ActivityUpdateCategoryPageBinding

class UpdateCategoryPage : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCategoryPageBinding //(mullatoez, 2021)

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_category_page)
        binding = ActivityUpdateCategoryPageBinding.inflate(layoutInflater) //(mullatoez, 2021)


        setContentView(binding.root)
        binding.imviewUpdateCate.setOnClickListener {
            pickImageFromGallery() //(mullatoez, 2021)
        }
        //setContentView(R.layout.activity_update_category_page)

        var help = HelperClass()

        val btnUpdateCat = findViewById<Button>(R.id.btnUpdateCategory)
        val txtName = findViewById<EditText>(R.id.txtUpdateCatName)
        val txtGoal = findViewById<EditText>(R.id.txtUpdateCatGoal)
        val cateImage = findViewById<ImageView>(R.id.imviewUpdateCate)

        val ID = intent.getStringExtra("ID")
        var trupassed: Int = 0
        val passedID = ID?.toInt()
        if (passedID != null){
            trupassed = passedID

        }

        var cur = help.getSelectedCategoriesObject(trupassed-1)

        Toast.makeText(this,cur.CatName, Toast.LENGTH_SHORT).show()

        //setting the textboxes with the current data
        (txtName as TextView).text = cur.CatName //(Kyay10, 2021)
        (txtGoal as TextView).text = cur.CatGoal.toString()
        cateImage.setImageResource(cur.CatImage)

        //updates the list
        btnUpdateCat.setOnClickListener{

            var convertedgoal = help.convertToInt(txtGoal.text.toString())

            help.updateCategory(trupassed, txtName.text.toString(), convertedgoal, cur.CatImage)

            val intent = Intent(this, categories::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK) //(mullatoez, 2021)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imviewUpdateCate.setImageURI(data?.data) //(mullatoez, 2021)
        }
    }
}