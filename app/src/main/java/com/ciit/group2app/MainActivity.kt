package com.ciit.group2app

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var tvDate: TextView
    private lateinit var btnShowDatePicker: Button
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var rePassword: EditText
    private lateinit var showPass: CheckBox
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var tcCheckbox: CheckBox
    private lateinit var registerButton: Button

    private val calendar = Calendar.getInstance()
    private var isDateSelected = false // Track if date has been selected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        // Basic Info
        firstName = findViewById(R.id.editFirstName)
        lastName = findViewById(R.id.editLastName)
        email = findViewById(R.id.editEmail)
        username = findViewById(R.id.editUsername)

        // Password
        password = findViewById(R.id.editPassword)
        rePassword = findViewById(R.id.editRePassword)
        showPass = findViewById(R.id.showPassCheckBox)

        // Gender
        genderRadioGroup = findViewById(R.id.genderRadioGroup)

        // Terms & Conditions
        tcCheckbox = findViewById(R.id.tc_checkbox)

        // Register Button
        registerButton = findViewById(R.id.regis_button)

        // Date Picker
        tvDate = findViewById(R.id.tvBirthdate)
        btnShowDatePicker = findViewById(R.id.datePicker)
    }

    private fun setupListeners() {
        btnShowDatePicker.setOnClickListener { showDatePicker() }

        showPass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                rePassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                rePassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            // Move cursor to end for better UX
            password.setSelection(password.text?.length ?: 0)
            rePassword.setSelection(rePassword.text?.length ?: 0)
        }

        genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.maleRadio -> showGenderToast("Male")
                R.id.femaleRadio -> showGenderToast("Female")
                R.id.othersRadio -> showGenderToast("Others")
            }
        }

        registerButton.setOnClickListener { validateAndRegister() }
    }

    private fun validateAndRegister() {
        val getFirstName = firstName.text.toString().trim()
        val getLastName = lastName.text.toString().trim()
        val getEmail = email.text.toString().trim()
        val getUsername = username.text.toString().trim()
        val getPassword = password.text.toString()
        val getRePassword = rePassword.text.toString()
        val isTcChecked = tcCheckbox.isChecked

        val selectedRadioButtonId = genderRadioGroup.checkedRadioButtonId

        when {
            getFirstName.isEmpty() || getLastName.isEmpty() -> {
                showErrorToast("Please enter your full name!")
            }
            getEmail.isEmpty() -> {
                showErrorToast("Please enter your email!")
            }
            getUsername.isEmpty() -> {
                showErrorToast("Please choose a username!")
            }
            getPassword.isEmpty() -> {
                showErrorToast("Please create a password!")
            }
            getRePassword.isEmpty() -> {
                showErrorToast("Please confirm your password!")
            }
            selectedRadioButtonId == -1 -> {
                showErrorToast("Please select your gender!")
            }
            !isTcChecked -> {
                showErrorToast("Please accept the Terms & Conditions!")
            }
            getPassword != getRePassword -> {
                showErrorToast("Passwords do not match!")
            }
            getPassword.length < 6 -> {
                showErrorToast("Password must be at least 6 characters!")
            }
            !isDateSelected -> { // NEW VALIDATION: Check if date is selected
                showErrorToast("Please select your date of birth!")
            }
            else -> {
                // Safe gender retrieval
                val getGender = if (selectedRadioButtonId != -1) {
                    genderRadioGroup.findViewById<RadioButton>(selectedRadioButtonId)?.text?.toString() ?: "Not specified"
                } else {
                    "Not specified"
                }

                // Get the selected date
                val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)

                showSuccessToast()

                // showDialogBox(getFirstName, getLastName, getEmail, getUsername, getPassword, getGender, selectedDate)

                // It will go to the PhotoGallery Activity
                val intent = Intent(this, PhotoGallery::class.java)
                intent.putExtra("email", email.text.toString())
                intent.putExtra("pass", password.text.toString())
                startActivity(intent)

            }
        }
    }

    private fun showSuccessToast() {
        val toast = Toast.makeText(this, "Registration successful! ⸜(｡˃ ᵕ ˂ )⸝♡", Toast.LENGTH_LONG)

        val view = toast.view
        view?.setBackgroundColor(0xFFE6E6FA.toInt()) // Light lavender purple

        val text = view?.findViewById<TextView>(android.R.id.message)
        text?.setTextColor(0xFF4B0082.toInt()) // Indigo text color
        text?.textSize = 16f
        text?.setTypeface(Typeface.create("serif", Typeface.BOLD)) // Georgia-like bold font
        text?.gravity = Gravity.CENTER

        toast.show()
    }

    private fun showGenderToast(gender: String) {
        val toast = Toast.makeText(this, "$gender selected", Toast.LENGTH_SHORT)

        val view = toast.view
        view?.setBackgroundColor(0xFFF0E6FA.toInt()) // Very light purple

        val text = view?.findViewById<TextView>(android.R.id.message)
        text?.setTextColor(0xFF6A0DAD.toInt()) // Purple text
        text?.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL))

        toast.show()
    }

    private fun showErrorToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)

        val view = toast.view
        view?.setBackgroundColor(0xFFFFE6E6.toInt()) // Light red/pink

        val text = view?.findViewById<TextView>(android.R.id.message)
        text?.setTextColor(0xFFD32F2F.toInt()) // Dark red text
        text?.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL))

        toast.show()
    }

    // Display User Info - UPDATED to include birthdate
    private fun showDialogBox(getFirstName: String, getLastName: String, getEmail: String, getUsername: String, getPassword: String, getGender: String, birthDate: String) {
        // Customize theme for alert box
        val titleView = TextView(this).apply {
            text = "Registration Successful!"
            textSize = 20f
            setTypeface(Typeface.create("serif", Typeface.BOLD))
            setTextColor(0xFF6A0DAD.toInt()) // Purple Text
            gravity = Gravity.CENTER
            setPadding(0, 40, 0, 20)
        }

        // Alert Box - Updated to include birthdate
        val builder = AlertDialog.Builder(this)
            .setCustomTitle(titleView)
            .setMessage("First Name: $getFirstName" +
                    "\nLast Name: $getLastName" +
                    "\nEmail: $getEmail" +
                    "\nUsername: $getUsername" +
                    "\nPassword: $getPassword" +
                    "\nGender: $getGender" +
                    "\nBirth Date: $birthDate" + // NEW: Added birthdate to dialog
                    "\nTerms & Conditions: Accepted")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = builder.create()

        // Set simple background color instead of missing drawable
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.white)

        alertDialog.show()
    }

    private fun showDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(year, monthOfYear, dayOfMonth)
            updateDateButton()
            isDateSelected = true // Mark date as selected
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            // Set maximum date to today
            datePicker.maxDate = System.currentTimeMillis()
            show()
        }
    }

    private fun updateDateButton() {
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        btnShowDatePicker.text = formattedDate

        // Update the label to show selected date
        tvDate.text = "Birthdate: $formattedDate"
    }
}