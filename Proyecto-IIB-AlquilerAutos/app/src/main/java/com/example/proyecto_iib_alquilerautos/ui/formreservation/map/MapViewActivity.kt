package com.example.proyecto_iib_alquilerautos.ui.formreservation.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.proyecto_iib_alquilerautos.MainActivity
import com.example.proyecto_iib_alquilerautos.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapViewActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        supportActionBar?.hide()

        val backButton: ImageButton = findViewById(R.id.returnButton)
        backButton.setOnClickListener {
            finish()
        }

        val finishButton: Button = findViewById(R.id.finish_button)
        finishButton.setOnClickListener {
            val intent = Intent(this@MapViewActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val viewOnMapButton: Button = findViewById(R.id.view_on_map)
        viewOnMapButton.setOnClickListener {
            val uri =
                "geo:$AGENCY_LATITUDE,$AGENCY_LONGITUDE?q=$AGENCY_LATITUDE,$AGENCY_LONGITUDE(Agência da Locadora)"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            mapIntent.setPackage("com.google.android.apps.maps")  // Define o pacote do aplicativo de mapas do Google
            startActivity(mapIntent)
        }

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        // Verificar permissões de localização
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initializeMap()
        } else {
            // Solicitar permissão de localização, se não estiver concedida
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
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

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Ativar controles do mapa
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Obter a localização atual do usuário
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val agencyLocation = LatLng(AGENCY_LATITUDE, AGENCY_LONGITUDE)
                    val userLocation = LatLng(location.latitude, location.longitude)

                    // Define a cor do marcador (azul neste exemplo)
                    val userMarker =
                        BitmapDescriptorFactory.fromResource(R.drawable.ic_elipse)

                    googleMap.addMarker(
                        MarkerOptions()
                            .position(agencyLocation)
                            .title("Agência da Locadora")
                    )

                    googleMap.addMarker(
                        MarkerOptions()
                            .position(userLocation)
                            .title("Usuário")
                            .icon(userMarker)
                    )

                    val routePolylineOptions = PolylineOptions()
                        .add(userLocation)
                        .add(agencyLocation)
                        .width(5f)
                        .color(ContextCompat.getColor(this, R.color.route_color))

                    googleMap.addPolyline(routePolylineOptions)

                    // Calcular os limites do mapa com base nas coordenadas dos dois pontos
                    val boundsBuilder = LatLngBounds.builder()
                    boundsBuilder.include(userLocation) // Adicionar a localização do usuário aos limites
                    boundsBuilder.include(agencyLocation) // Adicionar a localização da agência aos limites

                    val bounds = boundsBuilder.build()
                    val padding = 100 // Espaçamento em pixels ao redor dos limites
                    val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    googleMap.moveCamera(cameraUpdate)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeMap()
            }
        }
    }

    private fun initializeMap() {
        mapView.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
        private const val AGENCY_LATITUDE = -4.969732
        private const val AGENCY_LONGITUDE = -39.016754
    }
}
