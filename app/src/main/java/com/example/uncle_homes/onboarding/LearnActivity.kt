package com.example.uncle_homes.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class LearnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_sp)

        // Identifying CardViews by their IDs
        val cvTraining = findViewById<androidx.cardview.widget.CardView>(R.id.cvTraining)
        val cvSafety = findViewById<androidx.cardview.widget.CardView>(R.id.cvSafety)
        val cvPolicy = findViewById<androidx.cardview.widget.CardView>(R.id.cvPolicy)
        val cvEmployment = findViewById<androidx.cardview.widget.CardView>(R.id.cvEmployment)
        val cvAdditionalTips = findViewById<androidx.cardview.widget.CardView>(R.id.cvAdditionalTips)

        // Set click listeners for each card to show the respective bottom sheet
        cvTraining.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_training)
        }

        cvSafety.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_safety)
        }

        cvPolicy.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_policy)
        }

        cvEmployment.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_employment_verification)
        }

        cvAdditionalTips.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_additional_tips)
        }

        val proceedBtn: Button = findViewById(R.id.proceedBtn)

        proceedBtn.setOnClickListener {
            val intent = Intent(this, SecurityDocumentActivity::class.java)
            startActivity(intent)
        }
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
