package com.ciit.group2app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Activity 1 Button
        val activity1Button = findViewById<Button>(R.id.activity1Button)
        activity1Button.setOnClickListener { act1Validate() }

        // Activity 2 Button
        val activity2Button = findViewById<Button>(R.id.activity2Button)
        activity2Button.setOnClickListener { act2Validate() }

        // Activity 3 Button
        val activity3Button = findViewById<Button>(R.id.activity3Button)
        activity3Button.setOnClickListener { act3Validate() }
    }

    private fun act1Validate() {
        // Redirects to Login Activity
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
    }

    private fun act2Validate() {
        // Redirects to PhotoGallery Activity
        val intent = Intent(this, PhotoGallery::class.java)
        startActivity(intent)
    }

    private fun act3Validate() {
        // Redirects to Ordering System Activity
        val intent = Intent(this, OrderingSystem::class.java)
        startActivity(intent)
    }
}