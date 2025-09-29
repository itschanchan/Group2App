package com.example.experimentalapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.experimentalapp.R.id
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var tvDate : TextView
    lateinit var btnShowDatePicker : Button
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Basic Info
        val firstName = findViewById<EditText>(R.id.editFirstName)
        val lastName = findViewById<EditText>(R.id.editLastName)
        val email = findViewById<EditText>(R.id.editEmail)
        val username = findViewById<EditText>(R.id.editUsername)

        // Password
        val password = findViewById<EditText>(R.id.editPassword)
        val rePassword = findViewById<EditText>(R.id.editRePassword)
        val showPass = findViewById<CheckBox>(R.id.showPassCheckBox)

        // Radios
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)

        // Terms & Conditions
        val tcCheckbox = findViewById<CheckBox>(R.id.tc_checkbox)

        // Register Button
        val registerButton = findViewById<Button>(R.id.regis_button)

        tvDate = findViewById<TextView>(R.id.tvBirthdate)
        btnShowDatePicker = findViewById<Button>(R.id.datePicker)
        btnShowDatePicker.setOnClickListener {
            showDatePicker()
        }

        // Show Password Checkbox Method
        showPass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                rePassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                rePassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        // Gender Radio Button Group
        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
             when (checkedId) {
                R.id.maleRadio -> {
                    Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show()
                }
                R.id.femaleRadio -> {
                    Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show()
                    }
                R.id.othersRadio -> {
                    Toast.makeText(this, "Others", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Register Button Method
        registerButton.setOnClickListener {
            val getFirstName = firstName.text.toString()
            val getLastName = lastName.text.toString()
            val getEmail = email.text.toString()
            val getUsername = username.text.toString()
            val getPassword = password.text.toString()
            val getRePassword = rePassword.text.toString()
            val isTcChecked = tcCheckbox.isChecked

            var getRadio = "Not selected"
            val selectedRadioButtonId = genderRadioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                if (selectedRadioButton != null) {
                    getRadio = selectedRadioButton.text.toString()
                }
            }

            if (getFirstName.isEmpty() || getLastName.isEmpty() || getEmail.isEmpty() || getUsername.isEmpty() || getPassword.isEmpty() || getRePassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the basic info fields!", Toast.LENGTH_SHORT).show()
            } else if (selectedRadioButtonId == -1) { // Check if gender is selected
                 Toast.makeText(this, "Please select your gender!", Toast.LENGTH_SHORT).show()
            } else if (!isTcChecked) { // Check if T&C is accepted
                Toast.makeText(this, "Please accept the Terms & Conditions!", Toast.LENGTH_SHORT).show()
            } else if (getPassword != getRePassword) { // Check if passwords match
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                // Alert Box (PopUp)
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Registration Successful!")
                    .setMessage("First Name: $getFirstName" +
                            "\nLast Name: $getLastName" +
                            "\nEmail: $getEmail" +
                            "\nUsername: $getUsername" +
                            "\nPassword: $getPassword" +
                            "\nGender: $getRadio" +
                            "\nTerms & Conditions: Accepted")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                alertDialog.show()
            }
        }
    }

    // Date Picker Method
    private fun showDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate.time)

            btnShowDatePicker.text = formattedDate

        }

        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
