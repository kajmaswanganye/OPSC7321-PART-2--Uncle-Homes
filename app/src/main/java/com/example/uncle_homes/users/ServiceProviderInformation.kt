package com.example.uncle_homes.users

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.R
import com.example.uncle_homes.HomeActivity  // Import HomeActivity

class ServiceProviderInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp_user_information)

        // Reference to UI components
        val spinnerExperience: Spinner = findViewById(R.id.spinnerExperience)
        val editCertifications: EditText = findViewById(R.id.editCertifications)
        val editServiceArea: EditText = findViewById(R.id.editServiceArea)
        val editAvailability: EditText = findViewById(R.id.editAvailability)
        val radioGroupEquipment: RadioGroup = findViewById(R.id.radioGroupEquipment)
        val editPricing: EditText = findViewById(R.id.editPricing)
        val radioGroupInsurance: RadioGroup = findViewById(R.id.radioGroupInsurance)
        val editSpecialization: EditText = findViewById(R.id.editSpecialization)
        val editClientInteraction: EditText = findViewById(R.id.editClientInteraction)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)

        // Handle form submission
        buttonSubmit.setOnClickListener {
            // Collect input data
            val experience = spinnerExperience.selectedItem.toString()
            val certifications = editCertifications.text.toString()
            val serviceArea = editServiceArea.text.toString()
            val availability = editAvailability.text.toString()
            val equipment = if (radioGroupEquipment.checkedRadioButtonId == R.id.radioEquipmentYes) "Yes" else "No"
            val pricing = editPricing.text.toString()
            val insurance = if (radioGroupInsurance.checkedRadioButtonId == R.id.radioInsuranceYes) "Yes" else "No"
            val specialization = editSpecialization.text.toString()
            val clientInteraction = editClientInteraction.text.toString()

            // Display a Toast to confirm submission
            Toast.makeText(this, "Form Submitted", Toast.LENGTH_SHORT).show()

            // Handle the collected data (save to database, send to API, etc.)

            // Redirect user to HomeActivity
            val intent = Intent(this@ServiceProviderInformation, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
