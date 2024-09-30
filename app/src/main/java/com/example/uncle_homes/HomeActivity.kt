package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.Manifest
import android.app.ActivityOptions
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Random

class HomeActivity  : FragmentActivity(), OnMapReadyCallback {

    //Map variables
    private lateinit var myMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_REQUEST_CODE = 1000
    private val apiKey = "5ir2e7v22d2u" // This is eBird API key
    private var selectedDistance: Double = 5000.0 // Default distance in meters
    private lateinit var currentLocationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp_home_sp)

        // Initialize currentLocationTextView here
        currentLocationTextView = findViewById(R.id.current_location_text_view)

        // Other view initializations
        val profileCard: CardView = findViewById(R.id.profile)
        val tasksCard: CardView = findViewById(R.id.tasks)
        val featured1Card: CardView = findViewById(R.id.featured1)
        val featured2Card: CardView = findViewById(R.id.featured2)

        // Set click listeners for each CardView
        profileCard.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        tasksCard.setOnClickListener {
            val intent = Intent(this@HomeActivity, TasksActivity::class.java)
            startActivity(intent)
        }

        featured1Card.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_feature_1)
        }

        featured2Card.setOnClickListener {
            showBottomSheet(R.layout.bottom_sheet_feature_2)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Bottom navigation initialization
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> true
                R.id.jobs -> {
                    val intent = Intent(this@HomeActivity, JobsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.support -> {
                    val intent = Intent(this@HomeActivity, SupportActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.settings -> {
                    val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.home
    }


    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        checkLocationPermission()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                currentLocationTextView.text = "Location: ${currentLatLng.latitude}, ${currentLatLng.longitude}"
                ZoomOnMap(currentLatLng)
                addCustomMarkers(currentLatLng, selectedDistance)
            }
        }
    }

    fun ZoomOnMap(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .zoom(12.0f)
            .build()
        myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun addMarker(latLng: LatLng) {
        myMap.addMarker(MarkerOptions().position(latLng).title("Custom Marker"))
    }

    fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
        } else {
            myMap.isMyLocationEnabled = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                myMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun clearMarkers() {
        myMap.clear()
    }

    fun addCustomMarkers(currentLocation: LatLng, selectedDistance: Double) {
        val random = Random()
        val metersPerOffset = selectedDistance

        for (i in 1..20) {
            val offsetLat = (random.nextDouble() - 0.5) * 2 * 0.000008991 * metersPerOffset
            val offsetLng = (random.nextDouble() - 0.5) * 2 * 0.000011792 * metersPerOffset
            val customLatLng = LatLng(currentLocation.latitude + offsetLat, currentLocation.longitude + offsetLng)
            val customMarker = myMap.addMarker(MarkerOptions().position(customLatLng).title("Location $i"))

            customMarker?.tag = customLatLng

            myMap.setOnMarkerClickListener { marker ->
                val markerLocation = marker.tag as? LatLng
                if (markerLocation != null) {
                    requestDirectionsToMarker(currentLocation, markerLocation)
                }
                true
            }
        }
    }

    private fun requestDirectionsToMarker(currentLocation: LatLng, markerLatLng: LatLng) {
        val origin = "${currentLocation.latitude},${currentLocation.longitude}"
        val destination = "${markerLatLng.latitude},${markerLatLng.longitude}"

        val uri = "https://www.google.com/maps/dir/?api=1&origin=$origin&destination=$destination&travelmode=driving"
        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showToast("Google Maps app is not installed.")
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
