package com.example.uncle_homes

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_sp)

        // Initialize CardViews
        val cvUserAuthentication: CardView = findViewById(R.id.cvUserAuthentication)
        val cvLanguageAndRegion: CardView = findViewById(R.id.cvLanguageAndRegion)
        val cvNotificationsAndPreferences: CardView = findViewById(R.id.cvNotificationsAndPreferences)
        val cvDataUsage: CardView = findViewById(R.id.cvDataUsage)
        val cvFeedback: CardView = findViewById(R.id.cvFeedback)
        val cvDevicePermissions: CardView = findViewById(R.id.cvDevicePermissions)
        val cvLogout: CardView = findViewById(R.id.cvLogout)
        val cvDeleteAccount: CardView = findViewById(R.id.cvDeleteAccount)

        // Set up click listeners
        cvUserAuthentication.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_user_authentication) }
        cvLanguageAndRegion.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_change_language) }
        cvNotificationsAndPreferences.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_notifications) }
        cvDataUsage.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_data_usage) }
        cvFeedback.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_feedback) }
        cvDevicePermissions.setOnClickListener { showBottomSheet(R.layout.bottom_sheet_permissions) }
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
