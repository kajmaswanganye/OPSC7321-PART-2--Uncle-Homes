package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class InductionSlider : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleText: TextView
    private lateinit var descriptionText: TextView

    private val imageList = listOf(
        R.drawable.book_gif to Pair("Book Your Service", "Select a service and book it quickly."),
        R.drawable.payment_gif to Pair("Make a Payment", "To pay, add your card details securely."),
        R.drawable.track_gif to Pair("Track Your Tasks", "Keep track of all your service tasks."),
        R.drawable.manage_booking_gif to Pair("Service Updates", "Get real-time updates about your service."),
        R.drawable.add_task_gif to Pair("Cancel a Service", "Easily delete or cancel a scheduled service.")
    )

    private var currentIndex = 0
    private val displayTime = 5000L // 5 seconds for each GIF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induction_slider)

        imageView = findViewById(R.id.imageview)
        titleText = findViewById(R.id.titleText)
        descriptionText = findViewById(R.id.descriptionText)

        startImageSlideshow()
    }

    private fun startImageSlideshow() {
        val handler = Handler(Looper.getMainLooper())

        val runnable = object : Runnable {
            override fun run() {
                // Load the current GIF and update title and description
                val (imageRes, textPair) = imageList[currentIndex]
                val (title, description) = textPair

                // Load the GIF with a circular crop
                Glide.with(this@InductionSlider)
                    .load(imageRes)
                    .into(imageView)

                // Update title and description
                titleText.text = title
                descriptionText.text = description

                // Move to the next image, or redirect to MainCL activity after the last image
                currentIndex++
                if (currentIndex < imageList.size) {
                    handler.postDelayed(this, displayTime)
                } else {
                    // After all images, redirect to MainCL activity
                    val intent = Intent(this@InductionSlider, MainCL::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // Start the slideshow
        handler.post(runnable)
    }
}
