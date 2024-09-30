package com.example.uncle_homes.onboarding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.R
import androidx.cardview.widget.CardView

class SecurityDocumentActivity : AppCompatActivity() {

    // Request codes for different document types
    private val PICK_ID_PASSPORT = 1
    private val PICK_RESUME = 2
    private val PICK_CLEARANCE = 3
    private val PICK_ADDRESS = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_documents_sp)

        // Initialize CardViews for each document type
        val cvIDPassport: CardView = findViewById(R.id.cvIDPassport)
        val cvResume: CardView = findViewById(R.id.cvResume)
        val cvClearance: CardView = findViewById(R.id.cvClearance)
        val cvAddress: CardView = findViewById(R.id.cvAddress)

        // Set click listeners for each card view to open the file picker
        cvIDPassport.setOnClickListener {
            openFileChooser(PICK_ID_PASSPORT)
        }

        cvResume.setOnClickListener {
            openFileChooser(PICK_RESUME)
        }

        cvClearance.setOnClickListener {
            openFileChooser(PICK_CLEARANCE)
        }

        cvAddress.setOnClickListener {
            openFileChooser(PICK_ADDRESS)
        }

        // Proceed button listener
        val proceedBtn: Button = findViewById(R.id.proceedBtn)
        proceedBtn.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to open the file picker
    private fun openFileChooser(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*" // Allow all file types
        startActivityForResult(intent, requestCode)
    }

    // Handle the result of the file picker
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val fileUri: Uri? = data.data

            // Check which document was selected based on the request code
            when (requestCode) {
                PICK_ID_PASSPORT -> {
                    showToast("ID/Passport uploaded successfully!")
                }
                PICK_RESUME -> {
                    showToast("CV/Resume uploaded successfully!")
                }
                PICK_CLEARANCE -> {
                    showToast("Police Clearance uploaded successfully!")
                }
                PICK_ADDRESS -> {
                    showToast("Proof of Address uploaded successfully!")
                }
            }
        }
    }

    // Utility function to display toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
