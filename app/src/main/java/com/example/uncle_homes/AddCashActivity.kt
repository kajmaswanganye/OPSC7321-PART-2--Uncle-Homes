package com.example.uncle_homes

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCashActivity : AppCompatActivity() {

    private lateinit var cashInput: EditText
    private lateinit var addCashButton: Button
    private lateinit var totalCashText: TextView
    private val sharedPreferences by lazy { getSharedPreferences("cash_prefs", Context.MODE_PRIVATE) }
    private var totalCash: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_cash)

        // Initialize views
        cashInput = findViewById(R.id.cashInput)
        addCashButton = findViewById(R.id.addCashButton)
        totalCashText = findViewById(R.id.totalCashText)

        // Load the current total cash from SharedPreferences
        totalCash = sharedPreferences.getFloat("total_cash", 0.0f)
        updateTotalCashText()

        // Set click listener for the "Add Cash" button
        addCashButton.setOnClickListener {
            val cashReceived = cashInput.text.toString().toFloatOrNull()

            if (cashReceived != null && cashReceived > 0) {
                addCash(cashReceived)
            } else {
                Toast.makeText(this, "Please enter a valid cash amount", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addCash(amount: Float) {
        // Add the received cash to the total
        totalCash += amount

        // Store the updated total in SharedPreferences
        sharedPreferences.edit().putFloat("total_cash", totalCash).apply()

        // Update the total cash text
        updateTotalCashText()

        // Clear the input field
        cashInput.text.clear()

        // Show confirmation
        Toast.makeText(this, "Cash added successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun updateTotalCashText() {
        // Update the text view to display the current total cash
        totalCashText.text = "Current Total Cash: $%.2f".format(totalCash)
    }
}