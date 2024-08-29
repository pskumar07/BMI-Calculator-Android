package com.example.bmicalculator

import android.os.Bundle
import android.os.health.HealthStats
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var bmiResult: TextView
    private lateinit var healthStats: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bmiResult = findViewById(R.id.bmi_result)
        healthStats = findViewById(R.id.health_status)

        val height = findViewById<NumberPicker>(R.id.height)
        height.minValue = 80
        height.maxValue = 250
        height.value = 170

        val weight = findViewById<NumberPicker>(R.id.weight)
        weight.minValue = 30
        weight.maxValue = 250
        weight.value = 65

        height.setOnValueChangedListener { numberPicker, i ,i2 ->
            calculateBMI(height.value,weight.value)
        }
        weight.setOnValueChangedListener { numberPicker, i ,i2 ->
            calculateBMI(height.value,weight.value)
        }
    }
    private fun calculateBMI(height: Int,weight: Int){
        val bmi = weight/(height/100f*height/100f)
        bmiResult.text = "BMI "+String.format("%.2f" ,bmi)
        if(bmi<18.5){
            healthStats.text = "Underweight"
        }else if(bmi<30){
            healthStats.text = "Health"
        }else if(bmi<35){
            healthStats.text = "Overweight"
        }else{
            healthStats.text = "Obese"
        }
    }
}