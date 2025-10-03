package com.ciit.group2app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class Page1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val linkButton = findViewById<Button>(R.id.linkButton)
        linkButton.setOnClickListener {
            val url = "https://store.steampowered.com/app/367520/Hollow_Knight/"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }

        val back = findViewById<Button>(R.id.backButton)
        back.setOnClickListener {
            finish()
        }
    }
}