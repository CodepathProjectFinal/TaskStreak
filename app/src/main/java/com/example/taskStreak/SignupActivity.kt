package com.example.taskStreak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // OnClickListener for signup
        findViewById<Button>(R.id.signup_btnSignup).setOnClickListener {

            // Text from the 4 editTexts
            val username = findViewById<EditText>(R.id.signup_etUsername).text.toString()
            val password = findViewById<EditText>(R.id.signup_etPassword).text.toString()
            val passwordConfirm = findViewById<EditText>(R.id.signup_etPasswordConfirm).text.toString()

            // Check if passwords match
            if (password != passwordConfirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                signUpUser(username, password)
            }

        }

    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User successfully made new account
                Toast.makeText(this, "Successfully signed up", Toast.LENGTH_SHORT).show()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error signing up", Toast.LENGTH_SHORT).show()
            }
        }
    }
}