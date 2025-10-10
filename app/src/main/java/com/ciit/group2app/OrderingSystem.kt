package com.ciit.group2app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderingSystem : AppCompatActivity() {
    // Variables
    private lateinit var brands: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ordering_system)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Brand Selection
        brands = listOf(
            findViewById(R.id.gundam_witch_from_mercury_logo)
        )

        // Redirects to Gundam: Witch From Mercury Store
        brands[0].setOnClickListener {
            startActivity(Intent(this, Brand1::class.java))
        }

        // Back Button
        val back = findViewById<Button>(R.id.backButton)
        back.setOnClickListener {
            finish()
        }
    }
}