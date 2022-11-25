package com.example.testuiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testuiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

//        binding.bottomNavigation.setupWithNavController(navController)

        val animationAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha)
        binding.apply {

            btnToolbarBack.setOnClickListener {
                it.startAnimation(animationAlpha)
            }

            btnToolbarInfo.setOnClickListener {
                it.startAnimation(animationAlpha)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}