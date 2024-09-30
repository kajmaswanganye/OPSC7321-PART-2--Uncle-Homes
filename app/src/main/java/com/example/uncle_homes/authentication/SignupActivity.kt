package com.example.uncle_homes.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.InductionSlider
import com.example.uncle_homes.R
import com.example.uncle_homes.database.DatabaseHelper
import com.example.uncle_homes.onboarding.LearnActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        dbHelper = DatabaseHelper(this)

        val btnSignup: Button = findViewById(R.id.btnSignup)
        val roleRadioGroup: RadioGroup = findViewById(R.id.roleRadioGroup)
        val fullNameEditText: EditText = findViewById(R.id.fullName)
        val emailEditText: EditText = findViewById(R.id.email)
        val phoneEditText: EditText = findViewById(R.id.phoneNumber)
        val passwordEditText: EditText = findViewById(R.id.password)
        val confirmPasswordEditText: EditText = findViewById(R.id.confirm_password)

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout5)

        linearLayout.setOnClickListener {
            val intent = Intent(this, LoginActivity ::class.java)
            startActivity(intent)
            finish() // Close the intro activity
        }

        btnSignup.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            val selectedRoleId = roleRadioGroup.checkedRadioButtonId
            val selectedRoleButton: RadioButton = findViewById(selectedRoleId)
            val role = selectedRoleButton.text.toString()

            if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = dbHelper.addUser(fullName, email, phone, password, role)
            if (userId != -1L) {
                Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                // Navigate based on the selected role
                val intent = if (role == "Client") {
                    Intent(this, InductionSlider::class.java)
                } else {
                    Intent(this, LearnActivity::class.java)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}