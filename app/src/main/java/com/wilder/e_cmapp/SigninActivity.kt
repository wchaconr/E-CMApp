package com.wilder.e_cmapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.wilder.e_cmapp.mySQLLiteHelper
import com.wilder.e_cmapp.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    lateinit var binding: ActivitySigninBinding
    lateinit var usersDbHelper: mySQLLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db:FirebaseFirestore = FirebaseFirestore.getInstance()

        usersDbHelper= mySQLLiteHelper(this)

        binding.btnSignin.setOnClickListener {
            val firstname = binding.edtFirstName.text
            val lastname = binding.edtLastName.text
            val username = binding.edtUserName.text
            val email = binding.edtEmail.text
            val password = binding.edtPassword.text
            val confirmpasword = binding.edtconfirmPassword.text
            if (firstname.isNotBlank() && lastname.isNotBlank() && username.isNotBlank() && email.isNotBlank() && password.isNotBlank() &&confirmpasword.isNotBlank()){
                if(password.toString() == confirmpasword.toString()){
                    val data = hashMapOf(
                        "Firstname" to firstname.toString(),
                        "Lastname" to lastname.toString(),
                        "Email" to email.toString(),
                        "Password" to password.toString()
                    )
                    db.collection("Users")
                        .document(username.toString())
                        .set(data)
                        .addOnSuccessListener { result ->
                            Toast.makeText(this, R.string.signin_positive_process, Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener{ error ->
                            Toast.makeText(this, R.string.signin_negative_process, Toast.LENGTH_LONG).show()
                        }
                }else{
                    Toast.makeText(this, "The password does not match", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "It is necessary to fill all the fields", Toast.LENGTH_LONG).show()
            }
        }


    }
}