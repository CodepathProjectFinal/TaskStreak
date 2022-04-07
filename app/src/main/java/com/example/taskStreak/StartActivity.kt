package com.example.taskStreak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // If user is already logged in, go to ...
        if (ParseUser.getCurrentUser() != null) {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // OnClickListener for login
        findViewById<Button>(R.id.start_btnLogin).setOnClickListener {
            val intent = Intent(this@StartActivity, LoginActivity::class.java)
            startActivity(intent)
            //finish()
        }

        // OnClickListener for signup
        findViewById<Button>(R.id.start_btnSignup).setOnClickListener {
            val intent = Intent(this@StartActivity, SignupActivity::class.java)
            startActivity(intent)
            //finish()
        }

    }

}