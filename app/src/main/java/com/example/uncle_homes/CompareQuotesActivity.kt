package com.example.uncle_homes

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CompareQuotesActivity  : AppCompatActivity() {

    // Sample data class for a service provider quote
    data class ServiceProviderQuote(
        val providerName: String,
        val serviceType: String,
        val price: Double,
        val rating: Float
    )

    // List of service providers (mock data for demonstration)
    private val serviceProviders = listOf(
        ServiceProviderQuote("John's Cleaning", "Cleaner", 100.0, 4.5f),
        ServiceProviderQuote("Gardens R Us", "Gardener", 80.0, 4.0f),
        ServiceProviderQuote("Ace Electricians", "Electrician", 150.0, 4.8f),
        ServiceProviderQuote("Quick Plumbers", "Plumber", 120.0, 4.2f),
        ServiceProviderQuote("Pool Perfect", "Pool Cleaning", 90.0, 4.7f),
        ServiceProviderQuote("Tree Trimmers Inc.", "Tree Trimming", 75.0, 3.9f),
        ServiceProviderQuote("Pro Painters", "Painting", 200.0, 4.3f),
        ServiceProviderQuote("Pet Pals", "Pet Care", 60.0, 4.9f),
        ServiceProviderQuote("Carpentry Masters", "Carpentry", 180.0, 4.6f),
        ServiceProviderQuote("Bug Busters", "Pest Control", 110.0, 4.4f),
        ServiceProviderQuote("Sparkle Maids", "Cleaner", 95.0, 4.1f),
        ServiceProviderQuote("Green Thumb Experts", "Gardener", 85.0, 4.2f),
        ServiceProviderQuote("All Star Electric", "Electrician", 155.0, 4.9f),
        ServiceProviderQuote("Drain Doctors", "Plumber", 125.0, 4.0f),
        ServiceProviderQuote("Clear Blue Pools", "Pool Cleaning", 100.0, 4.6f),
        ServiceProviderQuote("Forest Friends Tree Services", "Tree Trimming", 82.0, 3.8f),
        ServiceProviderQuote("Fresh Coats", "Painting", 210.0, 4.7f),
        ServiceProviderQuote("Furry Friends Care", "Pet Care", 65.0, 4.7f),
        ServiceProviderQuote("Woodworks Co.", "Carpentry", 175.0, 4.5f),
        ServiceProviderQuote("Pest Pros", "Pest Control", 115.0, 4.1f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_quotes)

        // Initialize views
        val spinnerServiceType: Spinner = findViewById(R.id.spinnerServiceType)
        val listViewQuotes: ListView = findViewById(R.id.listViewQuotes)
        val sortByGroup: RadioGroup = findViewById(R.id.sortByGroup)

        // Set up adapter for the ListView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListOf())
        listViewQuotes.adapter = adapter

        // Filter and display quotes based on service type selection
        spinnerServiceType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long
            ) {
                val selectedService = parent?.getItemAtPosition(position).toString()
                displayQuotes(selectedService, adapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Sorting based on user selection
        sortByGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedService = spinnerServiceType.selectedItem.toString()
            when (checkedId) {
                R.id.sortByPrice -> displayQuotes(selectedService, adapter, sortBy = "price")
                R.id.sortByRating -> displayQuotes(selectedService, adapter, sortBy = "rating")
            }
        }
    }

    // Method to display quotes in the ListView
    private fun displayQuotes(serviceType: String, adapter: ArrayAdapter<String>, sortBy: String = "price") {
        val filteredQuotes = serviceProviders.filter { it.serviceType == serviceType }

        // Sort by price or rating
        val sortedQuotes = when (sortBy) {
            "price" -> filteredQuotes.sortedBy { it.price }
            "rating" -> filteredQuotes.sortedByDescending { it.rating }
            else -> filteredQuotes
        }

        // Update the ListView with formatted quotes
        adapter.clear()
        adapter.addAll(sortedQuotes.map {
            "${it.providerName}: \$${it.price} - Rating: ${it.rating}/5"
        })
        adapter.notifyDataSetChanged()
    }
}