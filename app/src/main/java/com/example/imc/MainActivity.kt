package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.buttonCalcular)
        val etPeso = findViewById<EditText>(R.id.editPeso)
        val etAltura = findViewById<EditText>(R.id.editAltura)
        val textResultado = findViewById<TextView>(R.id.text_resultado)

        btnCalcular.setOnClickListener {
            val peso = etPeso.text.toString().toInt()
            val altura = etAltura.text.toString().toDouble()

            val imc = calcularImc(peso, altura)

            textResultado.text = String.format("%.1f", imc)
        }
    }
}