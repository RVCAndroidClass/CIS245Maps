package edu.rvc.student.cis245maps
//map example for CIS245
//Android Course
//Rock Valley College
//Create NEW Project and Select Map Module
//1. Get Google Map API at https://developers.google.com/maps/
//2. Choose Get Started
//3. Choose Google Maps Android API
//4. Choose Get a Key
//5. Create a New Project and Get Key
//6. Add new key in app>manifests>androidmanifest.xml
//7. Add below code
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    var RVC = LatLng(0.0, 0.0)
    val ZOOM_LEVEL = 15f

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment : SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        var geocodeMatches: List<Address>? = null

        try {
            geocodeMatches = Geocoder(this).getFromLocationName(
                    "3301 N Muldford Rd, Rockford, IL 61114", 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (geocodeMatches != null) {
            latitude = geocodeMatches[0].latitude
            longitude = geocodeMatches[0].longitude
            RVC = LatLng(latitude, longitude)
        };

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return
        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(RVC, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(RVC).title("RockValleyCollege").snippet("Two year community college"))
        }
    }
}

