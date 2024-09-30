package com.example.uncle_homes

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditInformationSPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_information_sp)

        // Find the button by its ID
        val buttonSubmitEdit: Button = findViewById(R.id.buttonSubmitEdit)

        // Set a click listener on the button
        buttonSubmitEdit.setOnClickListener {
            // Show a toast message
            Toast.makeText(this, "Information Edited", Toast.LENGTH_SHORT).show()
        }
    }
}
