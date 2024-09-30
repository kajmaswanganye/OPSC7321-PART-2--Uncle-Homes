package com.example.uncle_homes

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinishOrderCL : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var jobSummaryTextView: TextView
    private lateinit var totalAmountTextView: TextView
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_finish_order_cl)

        // Initialize views
        progressBar = findViewById(R.id.progressBar)
        jobSummaryTextView = findViewById(R.id.jobSummaryTextView)
        totalAmountTextView = findViewById(R.id.totalAmountTextView)
        ratingBar = findViewById(R.id.ratingBar)

        // Animate the progress bar
        animateProgressBar()

        // Retrieve the job summary and total amount from the intent
        val selectedJobs = intent.getStringArrayListExtra("selectedJobs") ?: arrayListOf()
        val totalAmount = intent.getIntExtra("totalAmount", 0)

        // Update the job summary and total amount text views
        jobSummaryTextView.text = selectedJobs.joinToString("\n")
        totalAmountTextView.text = "Total Amount: R$totalAmount"

        // Handle rating submission
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // Handle rating value here, e.g., save it, display a message, etc.
        }
    }


    private fun animateProgressBar() {
        // Animate progress from 0 to 100%
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        animator.duration = 1000 // Duration in milliseconds (1 second)
        animator.start()
    }

    // Button click handlers
    fun ContinueFinish(view: View) {
        val intent = Intent(this, PublishOrderCL::class.java)
        startActivity(intent)
    }
}
