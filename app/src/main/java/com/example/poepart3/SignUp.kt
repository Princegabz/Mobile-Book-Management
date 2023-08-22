package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var HelpClass = HelperClass()

        //Assigning the fields to variables
        var textUsername = findViewById<EditText>(R.id.txtSignUpUsername)
        var txtEmailAdress = findViewById<EditText>(R.id.txtSignUpEmail)
        var txtPassword = findViewById<EditText>(R.id.txtSignUpPassword)
        //Declaring the buttons
        val button = findViewById<Button>(R.id.btnSignUp)

        //This is when a user already has an account, they can simply just click that button to redirect to login page
        val goToLoginPage = findViewById<TextView>(R.id.txtClickToSignIn)
        goToLoginPage.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        HelpClass.getUsers()


        button.setOnClickListener {

            HelpClass.AddAwardsUser(false, false, false )


            HelpClass.AddUser(textUsername.text.toString() // adding new user to the array
                ,txtEmailAdress.text.toString(), txtPassword.text.toString())


            HelpClass.AddCategory(1, "Fantasy", 0, R.drawable.fantasygenre)
            HelpClass.AddCategory(2, "Science Fiction", 0, R.drawable.scifigenre)
            HelpClass.AddCategory(3, "Self Help", 0, R.drawable.imggenreselfhelp)
            HelpClass.AddCategory(4, "History", 0, R.drawable.imghistorygenre)
            HelpClass.AddCategory(5, "Romance", 0, R.drawable.romancegenre)
            HelpClass.AddCategory(6, "Young Adult", 0, R.drawable.imgyoungadult)

//            HelpClass.AddBook(1, "Harry Potter and the goblet of fire", "20/03/2023",
//                "JK Rowling", 300, "Harry Potter", 3, R.drawable.imgplaceholder2, 1)


            val intent = Intent(this,SignIn::class.java)
            startActivity(intent) // go back to main activity

            Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
        }
    }
}