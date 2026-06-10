package com.example.inventoryapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DeatailedViewScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deatailed_view_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvGearList = findViewById<TextView>(R.id.tvGearList)
        val btnBackToBase = findViewById<Button>(R.id.btnBackToBase)

        Log.d("DetailedViewActivity", "Loading packing list")

        // Build the string to display all items
        var displayText = ""
        for (i in 0 until DataStore.itemCount) {
            displayText += "Item: ${DataStore.itemNames[i]}\n"
            displayText += "Category: ${DataStore.categories[i]}\n"
            displayText += "Quantity: ${DataStore.quantities[i]}\n"
            displayText += "Notes: ${DataStore.comments[i]}\n"
            displayText += "---------------------------\n"
        }

        if (DataStore.itemCount == 0) {
            tvGearList.text = "No gear packed yet."
        } else {
            tvGearList.text = displayText
        }

        // Return to Main Menu
        btnBackToBase.setOnClickListener {
            finish()
            Log.d("DetailedViewActivity", "Returning to Base Camp")
        }
    }
}