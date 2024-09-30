package com.example.uncle_homes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class TrackingActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var serviceProviderMarker: Marker? = null
    private val serviceProviderLocation = LatLng(-1.28333, 36.81667) // Example coordinates (Nairobi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking) // Replace with your actual layout resource

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Set initial position of service provider (if available)
        updateServiceProviderLocation(serviceProviderLocation)

        // Move the camera to the service provider's location and zoom in
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(serviceProviderLocation, 15f))

        // Simulate tracking: Update service provider's location as they move
        simulateServiceProviderMovement()
    }

    private fun updateServiceProviderLocation(location: LatLng) {
        if (serviceProviderMarker == null) {
            // If marker doesn't exist, create it
            serviceProviderMarker = googleMap.addMarker(
                MarkerOptions().position(location).title("Service Provider")
            )
        } else {
            // Move the existing marker
            serviceProviderMarker?.position = location
        }
    }

    // Simulate real-time movement (for demo purposes)
    private fun simulateServiceProviderMovement() {
        // Example route (you can replace this with real data from server or GPS)
        val route = listOf(
            LatLng(-1.28333, 36.81667), // Start location
            LatLng(-1.28270, 36.82195), // Mid location
            LatLng(-1.28558, 36.82490)  // End location
        )

        // Add a polyline to visualize the route
        googleMap.addPolyline(
            PolylineOptions().addAll(route).width(5f).color(resources.getColor(R.color.glossy_red))
        )

        // Simulate movement by updating the location marker along the route
        route.forEachIndexed { index, latLng ->
            mapView.postDelayed({
                updateServiceProviderLocation(latLng)
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            }, index * 2000L) // Delay of 2 seconds for each update
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}