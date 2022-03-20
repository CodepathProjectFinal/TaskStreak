package com.example.taskStreak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // OnClickListener for login
        findViewById<Button>(R.id.login_btnLogin).setOnClickListener {
            val username = findViewById<EditText>(R.id.login_etUsername).text.toString()
            val password = findViewById<EditText>(R.id.login_etPassword).text.toString()
            loginUser(username, password)
        }

    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                TODO("Go to main activity")
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    companion object {
        const val TAG = "LoginActivity"
    }

}