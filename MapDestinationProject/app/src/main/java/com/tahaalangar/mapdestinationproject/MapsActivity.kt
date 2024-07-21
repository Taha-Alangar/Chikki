package com.tahaalangar.mapproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.tahaalangar.mapdestinationproject.R
import com.tahaalangar.mapdestinationproject.databinding.ActivityMapsBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 1
    private var currentLocation: LatLng? = null
    private lateinit var etDestination: EditText
    private lateinit var btnGetDirections: Button
    private var isSettingsActivityStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etDestination = findViewById(R.id.et_destination)
        btnGetDirections = findViewById(R.id.btn_get_directions)

        btnGetDirections.setOnClickListener {
            getDirections()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getCurrentLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (checkLocationPermission()) {
            if (isLocationEnabled()) {
                val locationRequest = LocationRequest.create().apply {
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    interval = 10000 // 10 seconds
                    fastestInterval = 5000 // 5 seconds
                }

                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            } else {
                if (!isSettingsActivityStarted) {
                    Toast.makeText(this, "Please turn on location services", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    isSettingsActivityStarted = true
                    startActivity(intent)
                }
            }
        } else {
            requestLocationPermission()
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null) {
                    currentLocation = LatLng(location.latitude, location.longitude)
                    val currentLatLng = currentLocation
                    if (currentLatLng != null) {
                        mMap.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                        mFusedLocationClient.removeLocationUpdates(this) // Stop updates once we have the location
                    }
                } else {
                    Toast.makeText(this@MapsActivity, "Unable to get current location", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getDirections() {
        val destination = etDestination.text.toString()
        if (destination.isNotEmpty() && currentLocation != null) {
            val currentLat = currentLocation!!.latitude
            val currentLng = currentLocation!!.longitude
            val origin = "$currentLat,$currentLng"

            val url = "https://maps.googleapis.com/maps/api/directions/json?origin=$origin&destination=$destination&key=YOUR_API_KEY"

            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MapsActivity, "Failed to get directions", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val responseData = response.body?.string()
                        if (responseData != null) {
                            val json = JSONObject(responseData)
                            val routes = json.getJSONArray("routes")
                            if (routes.length() > 0) {
                                val points = routes.getJSONObject(0)
                                    .getJSONObject("overview_polyline")
                                    .getString("points")
                                val polylineOptions = PolylineOptions()
                                    .addAll(decodePoly(points))
                                    .color(android.graphics.Color.BLUE)
                                    .width(10f)
                                runOnUiThread {
                                    mMap.addPolyline(polylineOptions)
                                }
                            }
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this, "Please enter a destination and make sure location is enabled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat / 1E5, lng / 1E5)
            poly.add(p)
        }

        return poly
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkLocationPermission()) {
            if (isLocationEnabled()) {
                isSettingsActivityStarted = false
                getCurrentLocation()
            } else {
                if (!isSettingsActivityStarted) {
                    Toast.makeText(this, "Please turn on location services", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    isSettingsActivityStarted = true
                    startActivity(intent)
                }
            }
        } else {
            requestLocationPermission()
        }
    }
}
