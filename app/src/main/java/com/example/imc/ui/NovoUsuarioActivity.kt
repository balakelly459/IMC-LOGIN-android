package com.example.imc.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.imc.R
import com.example.imc.model.Usuario
import com.example.imc.utils.convertBitmapToBase64
import com.example.imc.utils.convertStringToLocalDate
import java.time.LocalDate
import java.util.*

const val CODE_IMAGE = 100

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var editProfissao: EditText
    lateinit var radioF: RadioButton
    lateinit var radioM: RadioButton
    lateinit var tvTrocarFoto: TextView
    lateinit var ivFotoPerfil: ImageView
    var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editNome = findViewById(R.id.edit_nome)
        editAltura = findViewById(R.id.edit_altura)
        editDataNascimento = findViewById(R.id.et_data)
        editProfissao = findViewById(R.id.edit_profissao)
        radioF = findViewById(R.id.radio_fem)
        radioM = findViewById(R.id.radio_masc)
        tvTrocarFoto = findViewById(R.id.tv_trocar_foto)
        ivFotoPerfil = findViewById(R.id.iv_foto_perfil)


        supportActionBar!!.title = "Novo usuário"

        //abrir a galeria de fotos para colocar foto de perfil
        tvTrocarFoto.setOnClickListener {
            abrirGaleria()
         }

        //Criar um calendário
        val calendario = Calendar.getInstance()

        //Determinar os dados (dia, mês e ano) do calendário
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker
        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->

                    var diaFinal = _dia
                    var mesFinal = _mes + 1

                    var mesString = "$mesFinal"
                    var diaString = "$diaFinal"

                    if (mesFinal < 10) {
                        mesString = "0$mesFinal"
                }

                    if (diaFinal < 10) {
                        diaString = "0$diaFinal"
                    }

                    Log.i("xpto", _dia.toString())
                    Log.i("xpto", _mes.toString())


                    editDataNascimento.setText("$diaString/$mesString/$_ano")
                }, ano, mes, dia
            )

            dp.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagem: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagem)

        if (requestCode == CODE_IMAGE && resultCode == -1) {
            //recuperar a imagem do stream
            val fluxoImagem = contentResolver.openInputStream(imagem!!.data!!)

            //converter os bits em um bitmap
            imageBitmap = BitmapFactory.decodeStream(fluxoImagem)

            // colocar o bitmap no imageview
            ivFotoPerfil.setImageBitmap(imageBitmap)
        }
    }

    private fun abrirGaleria(){

        // ABRIR GALERIA DE IMAGENS DO DISPOSITIVO
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        // abrir a activity resposável por exibir as imagens
        // esta activity retornará o conteúdo selecionado
        // para o nosso app
        startActivityForResult(
                Intent.createChooser(intent,
                "Escolha uma foto"),
                CODE_IMAGE
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (validarCampos()) {
            // Criar o objeto usuário
                val nascimento = convertStringToLocalDate(editDataNascimento.text.toString())
                val usuario = Usuario(
                        1,
                        editNome.text.toString(),
                        editEmail.text.toString(),
                        editSenha.text.toString(),
                        0,
                        editAltura.text.toString().toDouble(),
                        LocalDate.of(
                                nascimento.year,
                                nascimento.monthValue,
                                nascimento.dayOfMonth
                        ),
                        editProfissao.text.toString(),
                        if (radioF.isChecked) 'F' else 'M',
                        convertBitmapToBase64(imageBitmap!!)
                )

            //salvar o registro
            //em um SharedPreferences

            // A instrução abaixo irá criar um
            // arquivo sharedPreferences se nao existir
            //Se existir ele será aberto para edição
            val dados = getSharedPreferences(
                    "usuario", Context.MODE_PRIVATE)

            //Vamos criar o objeto que permitirá a
            //edição dos dados do arquivo SharedPreferences
            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profisssao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.putString("fotoPerfil", usuario.fotoPerfil)
            editor.apply()
        }

        Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show()

        return true
    }

    fun validarCampos(): Boolean {
        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "O e-mail é obrigatório!"
            valido = false
        }

        if (editSenha.text.isEmpty()) {
            editSenha.error = "A senha é obrigatória!"
            valido = false
        }
        if (editNome.text.isEmpty()) {
            editNome.error = "O nome é obrigatório!"
            valido = false
        }
        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "A profissão é obrigatória!"
            valido = false
        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "A altura é obrigatória!"
            valido = false
        }
        if (editDataNascimento.text.isEmpty()) {
            editDataNascimento.error = "A data é obrigatória!"
            valido = false
        }
        return valido
    }
}