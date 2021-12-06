package com.example.imc.repository

import android.content.Context
import com.example.imc.model.Pesagem

class PesagemRepository(var context: Context) {

    fun getListaPesagem(): List<Pesagem> {
        val listaPesagem = mutableListOf<Pesagem>()

        // ** Preencher a lista de pesagem
        val dados = context
                .getSharedPreferences(
                            "usuario",
                Context.MODE_PRIVATE)

        val pesosString = dados.getString("pesagem", "")
        // "51;54;53;52;56"
        val pesos = pesosString!!.split(";").toTypedArray()

        val datasString = dados.getString("data_pesagem", "")
        // "2021-11-29;2021-11-30;2021-12-02;2021-12-03;2021-12-06"
        val datas = datasString!!.split(";").toTypedArray()

        // ** Criar a lista de pesagem
        for (i in 0 until pesos.size){
            val pesagem = Pesagem(datas[i], pesos[i].toInt())
            listaPesagem.add(pesagem)
        }

        return listaPesagem
    }

}