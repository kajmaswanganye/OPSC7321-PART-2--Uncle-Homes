package com.example.uncle_homes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PostDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        // Initializing views
        val spinnerServiceType: Spinner = findViewById(R.id.spinnerServiceType)
        val editTextDuration: EditText = findViewById(R.id.editTextDuration)
        val editTextAvailability: EditText = findViewById(R.id.editTextAvailability)
        val editTextProviders: EditText = findViewById(R.id.editTextProviders)
        val editTextPrice: EditText = findViewById(R.id.editTextPrice)
        val buttonPostJob: Button = findViewById(R.id.buttonPostJob)

        // Set up the date picker dialog
        editTextAvailability.setOnClickListener {
            showDatePickerDialog(editTextAvailability)
        }

        // Handle the Post Job button click
        buttonPostJob.setOnClickListener {
            val serviceType = spinnerServiceType.selectedItem.toString()
            val duration = editTextDuration.text.toString()
            val availability = editTextAvailability.text.toString()
            val providers = editTextProviders.text.toString()
            val price = editTextPrice.text.toString()

            if (serviceType.isNotEmpty() && duration.isNotEmpty() && availability.isNotEmpty() &&
                providers.isNotEmpty() && price.isNotEmpty()) {

                // Post the job details (could be an API call or a database save)
                Toast.makeText(this, "Job Posted Successfully!", Toast.LENGTH_LONG).show()

                // Here you would typically send the data to a server or database

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Show date picker for availability
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editText.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}