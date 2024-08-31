package com.example.bmicalculator

import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var bmiResult: TextView
    private lateinit var healthStats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bmiResult = findViewById(R.id.bmi_result)
        healthStats = findViewById(R.id.health_status)

        val heightPicker = findViewById<NumberPicker>(R.id.height)
        heightPicker.minValue = 80
        heightPicker.maxValue = 250
        heightPicker.value = 170

        val weightPicker = findViewById<NumberPicker>(R.id.weight)
        weightPicker.minValue = 30
        weightPicker.maxValue = 250
        weightPicker.value = 65

        heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI(heightPicker.value, weightPicker.value)
        }
        weightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI(heightPicker.value, weightPicker.value)
        }

        // Initial BMI calculation
        calculateBMI(heightPicker.value, weightPicker.value)
    }

    private fun calculateBMI(height: Int, weight: Int) {
        if (height == 0) return  // Prevent division by zero
        val bmi = weight / (height / 100f * height / 100f)
        bmiResult.text = "BMI: ${String.format("%.2f", bmi)}"

        healthStats.text = when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Healthy"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }
}
