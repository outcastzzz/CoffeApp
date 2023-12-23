package com.register.coffeapp.presentation.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentMapBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding ?: throw RuntimeException("FragmentMapBinding == null")

    private lateinit var mapView: MapView

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

        mapView = binding.mapView.apply {
            map.move(CameraPosition(
                Point(55.751574, 37.573856),
                15.0f,
                0.0f,
                0.0f)
            )
        }

        for(point in points) {

            val latitude = point.latitude.toDouble()
            val longitude = point.longitude.toDouble()

            val yandexPoint = Point(latitude, longitude)

            Log.d("MapTag", "${yandexPoint.latitude}, ${yandexPoint.longitude}")

            mapView.map.mapObjects.addPlacemark(yandexPoint).apply {
                setIcon(ImageProvider.fromResource(context, R.drawable.coffee_marker))
            }
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}