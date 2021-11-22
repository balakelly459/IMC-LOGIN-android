package com.example.imc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.imc.R
import com.example.imc.utils.calcularIdade
import com.example.imc.utils.convertBase64ToBitmap
import org.w3c.dom.Text

class DashboardActivity : AppCompatActivity() {

    lateinit var  tvNome: TextView
    lateinit var tvProfissao: TextView
    lateinit var tvImc: TextView
    lateinit var tvNcd: TextView
    lateinit var tvPeso: TextView
    lateinit var tvIdade: TextView
    lateinit var tvAltura: TextView
    lateinit var ivPerfil: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvNome = findViewById(R.id.tv_nome)
        tvNcd = findViewById(R.id.tv_dash_ncd)
        tvPeso = findViewById(R.id.tv_peso)
        tvProfissao = findViewById(R.id.tv_profissao)
        tvIdade = findViewById(R.id.edit_idade)
        tvImc = findViewById(R.id.tv_dash_imc)
        tvNcd = findViewById(R.id.tv_dash_ncd)
        tvAltura = findViewById(R.id.tv_altura)
        ivPerfil = findViewById(R.id.iv_foto_perfil)

        carregarDashboard()
    }

    private fun carregarDashboard() {
        val arquivo =
            getSharedPreferences(
            "usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()
        tvIdade.text = calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()

        val bitmap = convertBase64ToBitmap(arquivo.getString("fotoPerfil", "")!!)
        ivPerfil.setImageBitmap(bitmap)

    }
}