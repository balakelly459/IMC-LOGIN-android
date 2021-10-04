package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()

        val textViewCriar = findViewById<TextView>(R.id.text_view_criar)

        textViewCriar.setOnClickListener {
            val abrirNovoUsuarioActivity =
                    Intent(this, NovoUsuarioActivity::class.java)
            startActivity(abrirNovoUsuarioActivity)
        }
    }
}