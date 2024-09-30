package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class SettingsCL : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_cl)

        // Initialize CardViews
        val cvLanguageAndRegion: CardView = findViewById(R.id.cvLanguageAndRegion)
        val cvNotificationsAndPreferences: CardView = findViewById(R.id.cvNotificationsAndPreferences)
        val cvDataUsage: CardView = findViewById(R.id.cvDataUsage)
        val cvFeedback: CardView = findViewById(R.id.cvFeedback)
        val cvDevicePermissions: CardView = findViewById(R.id.cvDevicePermissions)
        val cvLogout: CardView = findViewById(R.id.cvLogout)

        // Set up click listeners
        cvLanguageAndRegion.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_change_language) }
        cvNotificationsAndPreferences.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_notifications) }
        cvDataUsage.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_data_usage) }
        cvFeedback.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_feedback) }
        cvDevicePermissions.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_permissions) }

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> {
                    val intent = Intent(this@SettingsCL, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.btnHistory -> {
                    val intent = Intent(this@SettingsCL, HistoryCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this@SettingsCL, SupportCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnProfile -> {
                    val intent = Intent(this@SettingsCL, ProfileCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnSettings -> true
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.btnSettings


    }

    // Function to show the bottom sheet dialog
    private fun showBottomSheet(layoutResId: Int) {
        // Create the BottomSheetDialog
        val dialog = BottomSheetDialog(this)
        // Inflate the appropriate layout
        val view = LayoutInflater.from(this).inflate(layoutResId, null)

        // Find the dismiss button and set an onClickListener to close the dialog
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // Prevent dialog from closing when clicking outside of it
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }
}
