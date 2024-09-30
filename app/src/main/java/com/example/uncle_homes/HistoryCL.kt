package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryCL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history_cl)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.btnHome ->  {
                    val intent = Intent(this@HistoryCL, MainCL::class.java)
                    startActivity(intent)
                    true
                }

                R.id.btnHistory -> true

                R.id.btnHelp -> {
                    val intent = Intent(this@HistoryCL, SupportCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnProfile -> {
                    val intent = Intent(this@HistoryCL, ProfileCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnSettings -> {
                    val intent = Intent(this@HistoryCL, SettingsCL::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.btnHistory
    }
}