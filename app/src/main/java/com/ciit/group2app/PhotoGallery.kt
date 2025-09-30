package com.ciit.group2app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhotoGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val img1 = findViewById<ImageView>(R.id.donk1)

        val img2 = findViewById<ImageView>(R.id.shrek1)

        val img3 = findViewById<ImageView>(R.id.shrek2)

        val img4 = findViewById<ImageView>(R.id.donk2)

        val img5 = findViewById<ImageView>(R.id.donk3)

        val img6 = findViewById<ImageView>(R.id.shrek3)

        img1.setOnClickListener {
            val intent = Intent(this, Page1::class.java)
            startActivity(intent)
        }

        img2.setOnClickListener {
            val intent = Intent(this, Page2::class.java)
            startActivity(intent)
        }

        img3.setOnClickListener {
            val intent = Intent(this, Page3::class.java)
            startActivity(intent)
        }

        img4.setOnClickListener {
            val intent = Intent(this, Page4::class.java)
            startActivity(intent)
        }

        img5.setOnClickListener {
            val intent = Intent(this, Page5::class.java)
            startActivity(intent)
        }

        img6.setOnClickListener {
            val intent = Intent(this, Page6::class.java)
            startActivity(intent)
        }



    }
}
