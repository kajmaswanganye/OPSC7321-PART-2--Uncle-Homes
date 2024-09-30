package com.example.uncle_homes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.SyncStateContract.Helpers
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainCL : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var gridLayout: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_cl)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        searchBar = findViewById(R.id.searchBar)
        gridLayout = findViewById(R.id.gridLayout)

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterCardViews(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val cvBook: CardView = findViewById(R.id.cvBook)
        val cvTrackTask: CardView = findViewById(R.id.cvTrackTasks)
        val postDetails: CardView = findViewById(R.id.postDetails)
        val getQuoteCompare: CardView = findViewById(R.id.getQuoteCompare)

        cvBook.setOnClickListener {
            val intent = Intent(this@MainCL, BookCL::class.java)
            startActivity(intent)
        }

        cvTrackTask.setOnClickListener {
            val intent = Intent(this@MainCL, TrackingActivity::class.java)
            startActivity(intent)
        }

        postDetails.setOnClickListener {
            val intent = Intent(this@MainCL, PostDetailsActivity::class.java)
            startActivity(intent)
        }

        getQuoteCompare.setOnClickListener {
            val intent = Intent(this@MainCL, CompareQuotesActivity::class.java)
            startActivity(intent)
        }

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> true

                R.id.btnHistory -> {
                    val intent = Intent(this@MainCL, HistoryCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this@MainCL, SupportCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnProfile -> {
                    val intent = Intent(this@MainCL, ProfileCL::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnSettings -> {
                    val intent = Intent(this@MainCL, SettingsCL::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.btnHome
    }

    private fun filterCardViews(query: String) {
        val searchQuery = query.trim().lowercase()
        Log.d("Search", "Searching for: $searchQuery") // Debugging line

        for (i in 0 until gridLayout.childCount) {
            val cardView = gridLayout.getChildAt(i) as? CardView
            val tag = cardView?.tag as? String

            if (tag != null) {
                Log.d("Tag", "CardView tag: $tag") // Debugging line
                cardView.visibility = if (tag.lowercase().contains(searchQuery)) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            } else {
                cardView?.visibility = View.GONE
            }
        }
    }



    // Function for the first "Show More"
    fun ShowMore1(view: View) {
        // Find the parent LinearLayout of the clicked view
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(view.context).apply {
                layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
                text = "Painters"
                gravity = Gravity.CENTER // Center the text
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
                setBackgroundColor(Color.parseColor("#FFFFED"))
                setTextColor(Color.parseColor("#B5B5B5"))

                // Set the click listener for toggling back to ImageButton
                setOnClickListener { ShowMore1(it) }
            }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)

        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(view.context).apply {
                layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
                setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(R.drawable.paint_roller) // Set the image resource to your drawable
                id = R.id.imageButtonToReplace1

                // Set the click listener for toggling back to TextView
                setOnClickListener { OrderPainter(it) }
            }

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the second "Show More"
    fun ShowMore2(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Plumbers"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it, R.drawable.water_tap) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource(R.drawable.water_tap) // Set the image resource to your drawable
            imageButton.setOnClickListener({ OrderPainter(it) })
            imageButton.id = R.id.imageButtonToReplace2


            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the third "Show More"
    fun ShowMore3(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Electricians"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it, R.drawable.flash) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource(R.drawable.flash) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace3

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the fourth "Show More"
    fun ShowMore4(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Carpenters"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it, R.drawable.hand_saw) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource(R.drawable.hand_saw) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace4

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the fifth "Show More"
    fun ShowMore5(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Drivers"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it,  R.drawable.car) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource( R.drawable.car) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace5

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the sixth "Show More"
    fun ShowMore6(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Builders"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it,  R.drawable.hammer) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource(R.drawable.hammer) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace6

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the seventh "Show More"
    fun ShowMore7(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Gardeners"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it,  R.drawable.gardening) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        } else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource( R.drawable.gardening) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace7

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    // Function for the eighth "Show More"
    fun ShowMore8(view: View) {
        // Find the parent LinearLayout of the clicked TextView
        val parentLayout = view.parent as LinearLayout

        // Get the current view (ImageButton or TextView) that you want to toggle
        val currentView = parentLayout.getChildAt(1) // Assuming the ImageButton/TextView is at index 1

        if (currentView is ImageButton) {
            // Replace ImageButton with TextView
            val textView = TextView(this)
            textView.layoutParams = currentView.layoutParams // Copy the layout parameters from the ImageButton
            textView.text = "Pet Care"
            textView.gravity = Gravity.CENTER // Center the text
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Set the text size
            textView.setBackgroundColor(Color.parseColor("#FFFFED"))
            textView.setTextColor(Color.parseColor("#B5B5B5"))

            // Set the click listener for toggling back to ImageButton
            textView.setOnClickListener { toggleView(it,  R.drawable.pawprint) }

            // Remove the ImageButton and add the TextView in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(textView, index)
        }  else if (currentView is TextView) {
            // Replace TextView with ImageButton
            val imageButton = ImageButton(this)
            imageButton.layoutParams = currentView.layoutParams // Copy the layout parameters from the TextView
            imageButton.setBackgroundColor(Color.parseColor("#FFFFED")) // Set the background color
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            imageButton.setImageResource(R.drawable.pawprint) // Set the image resource to your drawable
            imageButton.id = R.id.imageButtonToReplace8

            // Remove the TextView and add the ImageButton in its place
            val index = parentLayout.indexOfChild(currentView)
            parentLayout.removeView(currentView)
            parentLayout.addView(imageButton, index)
        }
    }

    private fun toggleView(view: View, drawableId: Int)
    {

    }

    fun OrderPainter(view: View) {
        val intent = Intent(this, OrderServiceCL::class.java)

        // Pass the button's tag or any identifier to the OrderPainter activity
        intent.putExtra("buttonId", view.id)
        startActivity(intent)
    }

}
