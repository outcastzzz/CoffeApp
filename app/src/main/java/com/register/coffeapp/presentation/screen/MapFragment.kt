package com.register.coffeapp.presentation.screen

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment(), CameraListener {

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding ?: throw RuntimeException("FragmentMapBinding == null")

    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject
    private val startLocation = Point(59.9402, 30.315)
    private var zoomValue: Float = 16.5f

    private val geoObjectTapListener =
        GeoObjectTapListener { geoObjectTapEvent ->
            val selectionMetadata: GeoObjectSelectionMetadata = geoObjectTapEvent
                .geoObject
                .metadataContainer
                .getItem(GeoObjectSelectionMetadata::class.java)
            binding.mapView.map.selectGeoObject(selectionMetadata.id, selectionMetadata.layerId)
            false
        }

    private val mapObjectTapListener = MapObjectTapListener { _, _ -> true }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = MapFragmentArgs.fromBundle(requireArguments())
        val points = args.points

        moveToStartLocation()
        setMarkerInStartLocation()
        binding.mapView.map.addCameraListener(this)
        binding.mapView.map.addTapListener(geoObjectTapListener)

        binding.ivBackMap.setOnClickListener {
            launchCafeListFragment()
        }


        for(point in points) {

            val latitude = point.latitude.toDouble()
            val longitude = point.longitude.toDouble()

            val yandexPoint = Point(latitude, longitude)
            val cafeMarker = createBitmapFromVector(R.drawable.coffee_marker)

            mapObjectCollection.addPlacemark(yandexPoint, ImageProvider.fromBitmap(
                cafeMarker
            ))
        }

    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateReason: CameraUpdateReason,
        finished: Boolean
    ) {
        if (finished) {
            when {
                cameraPosition.zoom >= ZOOM_BOUNDARY && zoomValue <= ZOOM_BOUNDARY -> {
                    placemarkMapObject.setIcon(ImageProvider.fromBitmap(createBitmapFromVector(R.drawable.user_marker)))
                }
                cameraPosition.zoom <= ZOOM_BOUNDARY && zoomValue >= ZOOM_BOUNDARY -> {
                    placemarkMapObject.setIcon(ImageProvider.fromBitmap(createBitmapFromVector(R.drawable.user_marker)))
                }
            }
            zoomValue = cameraPosition.zoom
        }
    }

    private fun launchCafeListFragment() {
        findNavController().navigate(R.id.action_mapFragment_to_coffeeListFragment)
    }

    private fun setMarkerInStartLocation() {
        val marker = createBitmapFromVector(R.drawable.user_marker)
        mapObjectCollection = binding.mapView.map.mapObjects
        placemarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromBitmap(marker))
        placemarkMapObject.opacity = 0.5f
        placemarkMapObject.addTapListener(mapObjectTapListener)
    }

    private fun createBitmapFromVector(art: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(requireActivity().applicationContext, art) ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun moveToStartLocation() {
        binding.mapView.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 2f),
            null
        )
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("haveApiKey", true)
    }


    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }


    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    companion object {
        const val MAPKIT_API_KEY = "Ваш API-ключ"
        const val ZOOM_BOUNDARY = 16.4f
    }
}

