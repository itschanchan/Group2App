package com.ciit.group2app

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
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
    private lateinit var guelChkBx: CheckBox
    private lateinit var calibarnChkBx: CheckBox
    private lateinit var dilanzaChkBx: CheckBox
    private lateinit var sulettaQuantity: EditText
    private lateinit var miorineQuantity: EditText
    private lateinit var aerialQuantity: EditText
    private lateinit var guelQuantity: EditText
    private lateinit var calibarnQuantity: EditText
    private lateinit var dilanzaQuantity: EditText
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
        guelChkBx = findViewById(R.id.guelChkBx)
        calibarnChkBx = findViewById(R.id.calibarnChkBx)
        dilanzaChkBx = findViewById(R.id.dilanzaChkBx)

        sulettaQuantity = findViewById(R.id.sulettaQuantity)
        miorineQuantity = findViewById(R.id.miorineQuantity)
        aerialQuantity = findViewById(R.id.aerialQuantity)
        guelQuantity = findViewById(R.id.guelQuantity)
        calibarnQuantity = findViewById(R.id.calibarnQuantity)
        dilanzaQuantity = findViewById(R.id.dilanzaQuantity)

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
        guelQuantity.addTextChangedListener(quantityTextWatcher)
        dilanzaQuantity.addTextChangedListener(quantityTextWatcher)

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

        guelChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                guelQuantity.setText("1")
            } else {
                guelQuantity.setText("")
            }
        }

        calibarnChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                calibarnQuantity.setText("1")
            } else {
                calibarnQuantity.setText("")
            }
        }

        dilanzaChkBx.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dilanzaQuantity.setText("1")
            } else {
                dilanzaQuantity.setText("")
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
                showErrorDialog("Insufficient cash amount!\n｡ﾟ･ (>﹏<) ･ﾟ｡")
            }
        }
    }

    private fun calculateTotal(): Double {
        var sulettaTotal = 0.0
        var miorineTotal = 0.0
        var aerialTotal = 0.0
        var guelTotal = 0.0
        var calibarnTotal = 0.0
        var dilanzaTotal = 0.0

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
        if (guelChkBx.isChecked) {
            val guelPrice = 350.00
            val guelQty = guelQuantity.text.toString().toDoubleOrNull() ?: 0.0
            guelTotal = guelPrice * guelQty
        }
        if (calibarnChkBx.isChecked) {
            val calibarnPrice = 350.00
            val calibarnQty = calibarnQuantity.text.toString().toDoubleOrNull() ?: 0.0
            calibarnTotal = calibarnPrice * calibarnQty
        }
        if (dilanzaChkBx.isChecked) {
            val dilanzaPrice = 350.00
            val dilanzaQty = dilanzaQuantity.text.toString().toDoubleOrNull() ?: 0.0
            dilanzaTotal = dilanzaPrice * dilanzaQty
        }

        val totalAmount = sulettaTotal + miorineTotal + aerialTotal + guelTotal + calibarnTotal + dilanzaTotal
        displayTotal.setText(totalAmount.toString())
        return totalAmount
    }

    // Display Checkout Summary Dialog Box
    private fun showCheckoutSummary(totalAmount: Double, cashAmount: Double, changeAmount: Double) {

        val dialogView = layoutInflater.inflate(R.layout.custom_dialog_box, null)

        // Views inside the dialog
        val itemContainer = dialogView.findViewById<LinearLayout>(R.id.itemContainer)
        val totalTextView = dialogView.findViewById<TextView>(R.id.totalTextView)
        val cashTextView = dialogView.findViewById<TextView>(R.id.cashTextView)
        val changeTextView = dialogView.findViewById<TextView>(R.id.changeTextView)
        val okButton = dialogView.findViewById<Button>(R.id.btnOk)

        // Add itemized list
        if (sulettaChkBx.isChecked) {
            val qty = sulettaQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Suletta x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }
        if (miorineChkBx.isChecked) {
            val qty = miorineQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Miorine x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }
        if (aerialChkBx.isChecked) {
            val qty = aerialQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Aerial x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }
        if (guelChkBx.isChecked) {
            val qty = guelQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Guel x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }
        if (calibarnChkBx.isChecked) {
            val qty = calibarnQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Calibarn x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }
        if (dilanzaChkBx.isChecked) {
            val qty = dilanzaQuantity.text.toString()
            val itemTv = TextView(this).apply {
                text = "Dilanza x$qty"
                textSize = 16f
            }
            itemContainer.addView(itemTv)
        }

        // Set summary text
        totalTextView.text = "Total: $totalAmount"
        cashTextView.text = "Cash: $cashAmount"
        changeTextView.text = "Change: $changeAmount"

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        okButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

        // Changes the width of the dialog box
        val width = (300 * resources.displayMetrics.density).toInt()
        dialog.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun showErrorDialog(message: String) {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog_box, null)

        // Find views within the inflated dialogView
        val title = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val itemContainer = dialogView.findViewById<LinearLayout>(R.id.itemContainer)
        val cashTextView = dialogView.findViewById<TextView>(R.id.cashTextView)
        val totalTextView = dialogView.findViewById<TextView>(R.id.totalTextView)
        val changeTextView = dialogView.findViewById<TextView>(R.id.changeTextView)
        val okButton = dialogView.findViewById<Button>(R.id.btnOk)

        // Configure the views for the error dialog
        title.text = "Error"
        itemContainer.visibility = View.GONE
        cashTextView.visibility = View.GONE
        changeTextView.visibility = View.GONE

        // Reuse totalTextView for the error message
        totalTextView.visibility = View.VISIBLE
        totalTextView.text = message
        // To center the text, first make the TextView fill the width of its parent
        totalTextView.layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        // Now, setting the gravity will center the text within the TextView
        totalTextView.gravity = Gravity.CENTER


        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        okButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

        val width = (300 * resources.displayMetrics.density).toInt()
        dialog.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }
}
