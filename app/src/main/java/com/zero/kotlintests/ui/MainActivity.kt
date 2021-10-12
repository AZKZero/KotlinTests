package com.zero.kotlintests.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.zero.kotlintests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val x = ActivityMainBinding.inflate(layoutInflater)
        setContentView(x.root)

        x.button1.setOnClickListener {
            if (!x.fragmentContainerView.findNavController().popBackStack()) {
                finish()
            }
        }
    }
}