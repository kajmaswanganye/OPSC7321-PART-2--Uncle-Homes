package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupportCL: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_cl)

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> {
                    val intent = Intent(this@SupportCL, MainCL::class.java)
                    startActivity(intent)
                    true
                }

                R.id.btnHistory -> {
                    val intent = Intent(this@SupportCL, HistoryCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnHelp -> true

                R.id.btnProfile -> {
                    val intent = Intent(this@SupportCL, ProfileCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnSettings -> {
                    val intent = Intent(this@SupportCL, SettingsCL::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.btnHelp
    }
}
