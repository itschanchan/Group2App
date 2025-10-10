package com.ciit.group2app

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Brand1 : AppCompatActivity() {
    // Initialized Variables
    private lateinit var sulettaChkBx: CheckBox
    private lateinit var miorineChkBx: CheckBox
    private lateinit var aerialChkBx: CheckBox
    private lateinit var sulettaQuantity: EditText
    private lateinit var miorineQuantity: EditText
    private lateinit var aerialQuantity: EditText
    private lateinit var editCashAmount: EditText
    private lateinit var checkoutButton: Button
    private lateinit var displayTotal: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_brand1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        sulettaChkBx = findViewById(R.id.sulettaChkBx)
        miorineChkBx = findViewById(R.id.miorineChkBx)
        aerialChkBx = findViewById(R.id.aerialChkBx)
        sulettaQuantity = findViewById(R.id.sulettaQuantity)
        miorineQuantity = findViewById(R.id.miorineQuantity)
        aerialQuantity = findViewById(R.id.aerialQuantity)
        editCashAmount = findViewById(R.id.editCashAmount)
        checkoutButton = findViewById(R.id.checkoutButton)
        displayTotal = findViewById(R.id.displayTotal)

        // Back to Main Menu
        val back = findViewById<Button>(R.id.backButton)
        back.setOnClickListener {
            finish()
        }

        /* TextWatcher - allows you to monitor and respond to changes in the text content
                         of an Editable object, such as an EditText.
        */
        // TextWatcher for quantity fields to auto-calculate total
        val quantityTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        sulettaQuantity.addTextChangedListener(quantityTextWatcher)
        miorineQuantity.addTextChangedListener(quantityTextWatcher)
        aerialQuantity.addTextChangedListener(quantityTextWatcher)

        // Auto-set quantity to 1 on check
        sulettaChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sulettaQuantity.setText("1")
            } else {
                sulettaQuantity.setText("")
            }
        }

        miorineChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                miorineQuantity.setText("1")
            } else {
                miorineQuantity.setText("")
            }
        }

        aerialChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                aerialQuantity.setText("1")
            } else {
                aerialQuantity.setText("")
            }
        }

        // Calculation Logics to be displayed on Checkout :)
        checkoutButton.setOnClickListener {
            val totalAmount = calculateTotal()
            val cashAmount = editCashAmount.text.toString().toDoubleOrNull() ?: 0.0

            if (cashAmount >= totalAmount) {
                val changeAmount = cashAmount - totalAmount
                showCheckoutSummary(totalAmount, cashAmount, changeAmount)
            } else {
                showErrorDialog("Insufficient cash amount.")
            }
        }
    }

    private fun calculateTotal(): Double {
        var sulettaTotal = 0.0
        var miorineTotal = 0.0
        var aerialTotal = 0.0

        if (sulettaChkBx.isChecked) {
            val sulettaPrice = 350.00
            val sulettaQty = sulettaQuantity.text.toString().toDoubleOrNull() ?: 0.0
            sulettaTotal = sulettaPrice * sulettaQty
        }
        if (miorineChkBx.isChecked) {
            val miorinePrice = 350.00
            val miorineQty = miorineQuantity.text.toString().toDoubleOrNull() ?: 0.0
            miorineTotal = miorinePrice * miorineQty
        }
        if (aerialChkBx.isChecked) {
            val aerialPrice = 350.00
            val aerialQty = aerialQuantity.text.toString().toDoubleOrNull() ?: 0.0
            aerialTotal = aerialPrice * aerialQty
        }
        val totalAmount = sulettaTotal + miorineTotal + aerialTotal
        displayTotal.setText(totalAmount.toString())
        return totalAmount
    }

    // Display Checkout Summary Dialog Box
    private fun showCheckoutSummary(totalAmount: Double, cashAmount: Double, changeAmount: Double) {
        // Customize theme for alert box
        val titleView = TextView(this).apply {
            text = "Checkout Summary"
            textSize = 20f
            setTypeface(Typeface.create("serif", Typeface.BOLD))
            setTextColor(0xFF6A0DAD.toInt()) // Purple Text
            gravity = Gravity.CENTER
            setPadding(0, 40, 0, 20)
        }

        val message = "Total: $totalAmount\n" +
                "Cash: $cashAmount\n" +
                "Change: $changeAmount"
        val builder = AlertDialog.Builder(this)
            .setCustomTitle(titleView)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}
