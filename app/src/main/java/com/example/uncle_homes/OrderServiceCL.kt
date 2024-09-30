package com.example.uncle_homes


import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderServiceCL : AppCompatActivity() {

    private lateinit var jobListContainer: LinearLayout
    private lateinit var totalAmountTextView: TextView
    private lateinit var progressBar: ProgressBar // Declare ProgressBar variable
    private var totalAmount: Int = 0

    private val calloutFee = 50
    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_painter_cl)

        // Initialize UI elements
        jobListContainer = findViewById(R.id.jobListContainer)
        totalAmountTextView = findViewById(R.id.totalAmountTextView)
        progressBar = findViewById(R.id.progressBar)// Initialize ProgressBar

        animateProgressBar()

        // Retrieve the button ID from the intent
        buttonId = intent.getIntExtra("buttonId", -1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun animateProgressBar() {
        // Animate progress from 0 to 33%
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 25)
        animator.duration = 1000 // Duration in milliseconds (e.g., 1 second)
        animator.start()
    }

    fun GoHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun showJobOptionsDialog(view: View) {
        val jobs: Array<String>
        val prices: IntArray

        when (buttonId) {
            R.id.imageButtonToReplace1 -> {
                jobs = arrayOf("Paint removal (R30)", "Waterproof painting (R50)", "Spray painting (R100)")
                prices = intArrayOf(30, 50, 100)
            }
            R.id.imageButtonToReplace2 -> {
                jobs = arrayOf("Fix leaking pipe (R40)", "Install faucet (R55)")
                prices = intArrayOf(40, 55)
            }
            R.id.imageButtonToReplace3 -> {
                jobs = arrayOf("Install light fixture (R60)", "Repair outlet (R75)")
                prices = intArrayOf(60, 75)
            }
            R.id.imageButtonToReplace4 -> {
                jobs = arrayOf("Build shelf (R80)", "Repair door (R90)")
                prices = intArrayOf(80, 90)
            }
            R.id.imageButtonToReplace5 -> {
                jobs = arrayOf("Transport goods (R100)", "Delivery service (R120)")
                prices = intArrayOf(100, 120)
            }
            R.id.imageButtonToReplace6 -> {
                jobs = arrayOf("Concrete work (R200)", "Bricklaying (R250)")
                prices = intArrayOf(200, 250)
            }
            R.id.imageButtonToReplace7 -> {
                jobs = arrayOf("Lawn mowing (R50)", "Hedge trimming (R60)")
                prices = intArrayOf(50, 60)
            }
            R.id.imageButtonToReplace8 -> {
                jobs = arrayOf("Dog walking (R30)", "Pet grooming (R45)")
                prices = intArrayOf(30, 45)
            }
            else -> {
                jobs = arrayOf()
                prices = intArrayOf()
            }
        }

        val selectedJobs = mutableListOf<String>()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Jobs")
            .setItems(jobs) { _, which ->
                val job = jobs[which]
                val price = prices[which]
                selectedJobs.add(job)
                addJob(job, price)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun addJob(job: String, price: Int) {
        val jobTextView = TextView(this).apply {
            text = job
            textSize = 18f
            setPadding(0, 8, 0, 8)
        }
        jobListContainer.addView(jobTextView)

        // Update total amount
        totalAmount += price
        updateTotalAmount()
    }

    private fun updateTotalAmount() {
        // Add the callout fee to the total amount
        val totalWithCalloutFee = totalAmount + calloutFee
        totalAmountTextView.text = "Total: R$totalWithCalloutFee (includes callout fee of R$calloutFee)"
    }

    fun GoToCheckOut(view: View) {
        val jobDescriptions = ArrayList<String>()

        // Get all job descriptions from the container
        for (i in 0 until jobListContainer.childCount) {
            val jobTextView = jobListContainer.getChildAt(i) as TextView
            jobDescriptions.add(jobTextView.text.toString())
        }

        // Prepare the intent to start CheckOut activity
        val intent = Intent(this, CheckOutCL::class.java).apply {
            putStringArrayListExtra("selectedJobs", jobDescriptions)
            putExtra("totalAmount", totalAmount + calloutFee)
        }

        // Start the CheckOut activity
        startActivity(intent)
    }
}