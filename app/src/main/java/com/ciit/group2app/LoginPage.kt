package com.ciit.group2app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signupButton = findViewById<Button>(R.id.signupButton)
        signupButton.setOnClickListener { regisValidate() }

        val demoButton = findViewById<Button>(R.id.demoButton)
        demoButton.setOnClickListener { validate() }

        // Back to Main Menu
        val back = findViewById<Button>(R.id.backButton)
        back.setOnClickListener {
            finish()
        }
    }

    private fun validate() {
        // It will go to the PhotoGallery Activity
        val intent = Intent(this, PhotoGallery::class.java)
        startActivity(intent)
    }

    private fun regisValidate() {
        // Redirects to Registration Activity
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
    }


}