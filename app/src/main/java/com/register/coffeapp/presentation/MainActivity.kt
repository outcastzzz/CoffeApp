package com.register.coffeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.register.coffeapp.R
import com.register.coffeapp.databinding.ActivityMainBinding
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setApiKey(savedInstanceState)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
    }

    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey") ?: false
        if (!haveApiKey) MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("haveApiKey", true)
    }

    companion object {
        const val MAPKIT_API_KEY = "19bca4c9-8ba8-42ff-a7c6-beac1df7e251"
    }

}