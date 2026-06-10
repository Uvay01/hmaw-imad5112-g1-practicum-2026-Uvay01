package com.example.inventoryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mainscreen : AppCompatActivity() {

    private lateinit var tvTotalItems: TextView
    private lateinit var btnAddGear: Button
    private lateinit var btnViewGear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainscreen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTotalItems = findViewById(R.id.tvTotalItems)
        btnAddGear = findViewById(R.id.btnAddGear)
        btnViewGear = findViewById(R.id.btnViewGear)

        // Navigation to Add Gear screen
        btnAddGear.setOnClickListener {
            Log.d("Mainscreen", "Add Gear button clicked")
            val intent = Intent
            startActivity(intent)
        }

        // Navigate to Detailed View screen
        btnViewGear.setOnClickListener {
            Log.d("Mainscreen", "View Gear button clicked")
            val intent = Intent(this, DetailedViewScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Calculate total items
        var total = 0
        for (i in 0 until DataStore.itemCount) {
            total += DataStore.quantities[i]
        }
        tvTotalItems.text = "Total Items Packed: $total"
        Log.d("Mainscreen", "Total items calculated: $total")
    }
}