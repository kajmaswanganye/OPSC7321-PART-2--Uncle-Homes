package com.example.uncle_homes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.database.DatabaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class ProfileCL : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_cl)

        dbHelper = DatabaseHelper(this)

        // Retrieve user email from SharedPreferences (you need to implement this)
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPref.getString("USER_EMAIL", "") ?: ""

        // Fetch user data
        val userData = dbHelper.getUserData(userEmail)

        // Update UI with user data
        userData?.let {
            findViewById<TextView>(R.id.full_name).text = it.fullName
            findViewById<TextView>(R.id.email).text = it.email
            findViewById<TextView>(R.id.phone_number).text = it.phone
        }

        // Find the MaterialCardView elements by their IDs
        val cvWallet: MaterialCardView = findViewById(R.id.cvWallet)
        val cvEditInformation: MaterialCardView = findViewById(R.id.cvEditInformation)
        val cvAnalytics: MaterialCardView = findViewById(R.id.cvAnalytics)
        val cvQuestions: MaterialCardView = findViewById(R.id.cvQuestions)

        // Set up click listeners for each card view
        cvWallet.setOnClickListener {
            openActivity(WalletCL::class.java)
        }

        cvEditInformation.setOnClickListener {
            openActivity(EditInformationCLActivity::class.java)
        }

        cvAnalytics.setOnClickListener {
            openActivity(AnalyticsCL::class.java)
        }

        cvQuestions.setOnClickListener {
            openActivity(HelpCL::class.java)
        }

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> {
                    val intent = Intent(this@ProfileCL, MainCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnHistory -> {
                    val intent = Intent(this@ProfileCL, HistoryCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this@ProfileCL, SupportCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnProfile -> true
                R.id.btnSettings -> {
                    val intent = Intent(this@ProfileCL, SettingsCL::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.btnProfile
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}