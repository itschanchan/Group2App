package com.ciit.group2app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.gridlayout.widget.GridLayout

class PhotoGallery : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var currentProgress = 0
    private val clickedImages = mutableSetOf<View>()
    private lateinit var seekBar: SeekBar
    private lateinit var images: List<ImageView>
    private lateinit var gridLayout: GridLayout
    private lateinit var hiddenText: TextView
    private lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        progressBar = findViewById(R.id.progressBar)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        seekBar = findViewById(R.id.seekBar)
        gridLayout = findViewById(R.id.gridLayout)
        hiddenText = findViewById(R.id.hidden_text)
        toggleButton = findViewById(R.id.toggleButton)

        images = listOf(
            findViewById(R.id.ivSilksong),
            findViewById(R.id.ivTatsumaki),
            findViewById(R.id.ivShrek),
            findViewById(R.id.ivDonkey),
            findViewById(R.id.ivTenna),
            findViewById(R.id.ivGoldShip)
        )

        // Setup progress bar
        progressBar.max = images.size

        // Setup click listeners
        images[0].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page1::class.java))
        }

        images[1].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page2::class.java))
        }

        images[2].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page3::class.java))
        }

        images[3].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page4::class.java))
        }

        images[4].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page5::class.java))
        }

        images[5].setOnClickListener { view ->
            updateProgressOnClick(view)
            startActivity(Intent(this, Page6::class.java))
        }

        // LOGOUT
        logoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // SeekBar setup
        seekBar.max = images.size
        seekBar.progress = images.size // Show all images initially
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                images.forEachIndexed { index, imageView ->
                    if (index < progress) {
                        imageView.visibility = View.VISIBLE
                    } else {
                        imageView.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        // ToggleButton (Hide/Show Gallery)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // shows the Grid Layout
                gridLayout.visibility = View.VISIBLE
                // Hides the text
                hiddenText.visibility = View.GONE

                progressBar.visibility = View.VISIBLE
                seekBar.visibility = View.VISIBLE
            } else {
                // hides the Grid Layout
                gridLayout.visibility = View.GONE
                progressBar.visibility = View.GONE
                seekBar.visibility = View.GONE

                // Shows the text
                hiddenText.visibility = View.VISIBLE


            }
        }
    }

    // Counts the no. of images clicked and it will appear in the progress bar.
    private fun updateProgressOnClick(view: View) {
        if (clickedImages.add(view)) { // .add() returns true if the element was not already in the set
            currentProgress++
            progressBar.progress = currentProgress
            // Nested if that will show a toast if all images are clicked.
            if (currentProgress == progressBar.max) {
                Toast.makeText(this, "Congratulations! You clicked all the images! ╰(▔∀▔)╯", Toast.LENGTH_LONG).show()
            }
        }
    }
}
