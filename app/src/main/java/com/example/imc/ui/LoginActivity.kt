 package com.example.imc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.imc.R
import com.example.imc.utils.autenticar

 class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()

        val textViewCriar = findViewById<TextView>(R.id.text_view_criar)
        val buttonEntrar = findViewById<Button>(R.id.button_entrar)

        val editEmail = findViewById<EditText>(R.id.edit_text_email)
        val editSenha = findViewById<EditText>(R.id.edit_text_senha)

        buttonEntrar.setOnClickListener {
            val autenticou = autenticar(
                editEmail.text.toString(),
                editSenha.text.toString(),
                this)

            if (autenticou) {
                val intent = Intent(
                    this,
                    DashboardActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }

        textViewCriar.setOnClickListener {
            val abrirNovoUsuarioActivity =
                    Intent(this, NovoUsuarioActivity::class.java)
            startActivity(abrirNovoUsuarioActivity)
        }
    }
}