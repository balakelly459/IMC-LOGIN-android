package com.example.imc.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun convertStringToLocalDate(brazilDate: String) : LocalDate {

    val dateFormatterFromBrazil = DateTimeFormatter
            .ofPattern("dd/MM/yyyy")

    val localDateFormat = LocalDate
            .parse(brazilDate, dateFormatterFromBrazil)

    return localDateFormat
}

//fun calcularIdade(dataAtual: Date, nascimento: Date) : Int {

//}