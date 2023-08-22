package com.example.poepart3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var HelpClass = HelperClass()
//        var arr = HelpClass.getUsersArrray()


        //Variablesis being declared here
        var editTextName = findViewById<EditText>(R.id.txtUserName)
        var editTextPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtGoToRegisterPage = findViewById<TextView>(R.id.txtGoToRegisterPage)

//        val database = Firebase.database
//        val myRef = database.getReference("users")
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for (empSnap in snapshot.children){
//                        val empdata = empSnap.getValue(Users::class.java)
//                        arr.add(empdata!!)
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })

        btnLogin.setOnClickListener {


            var shouldBeAllowedIn = HelpClass.checkUser(editTextName.text.toString(),editTextPassword.text.toString())
            // Checking the user
            shouldBeAllowedIn
            if (shouldBeAllowedIn) // if user is allowed to go welcome page
            {
                val intent = Intent(this,categories::class.java)
                startActivity(intent)
            }
            else
            { // otherwise user not allowed error message
                Toast.makeText(this,"Detail entered were incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        txtGoToRegisterPage.setOnClickListener {
            //This line of code is used if user does not have an account
            val goToRegisterPage: TextView = findViewById(R.id.txtGoToRegisterPage)
            goToRegisterPage.setOnClickListener{
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }
        }
    }

}