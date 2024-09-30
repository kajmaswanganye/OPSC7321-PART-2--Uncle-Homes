package com.example.uncle_homes.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.InductionSlider
import com.example.uncle_homes.R
import com.example.uncle_homes.database.DatabaseHelper
import com.example.uncle_homes.onboarding.LearnActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val roleRadioGroup: RadioGroup = findViewById(R.id.roleRadioGroup)
        val emailEditText: EditText = findViewById(R.id.email)
        val passwordEditText: EditText = findViewById(R.id.password)

        btnLogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val selectedRoleId = roleRadioGroup.checkedRadioButtonId
            val selectedRoleButton: RadioButton = findViewById(selectedRoleId)
            val selectedRole = selectedRoleButton.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (isAuthenticated, userRole) = dbHelper.getUserByEmailAndPassword(email, password)
            if (isAuthenticated && userRole == selectedRole) {
                // Store user email in SharedPreferences
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("USER_EMAIL", email)
                    apply()
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate based on the user's role
                val intent = if (userRole == "Client") {
                    Intent(this, InductionSlider::class.java)
                } else {
                    Intent(this, LearnActivity::class.java)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials or wrong role selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}