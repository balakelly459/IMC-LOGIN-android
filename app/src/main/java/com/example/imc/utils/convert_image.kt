package com.example.imc.utils

import android.graphics.Bitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

fun convertBitmapToBase64(bitmap: Bitmap) : String {
    val bitmapArray = ByteArrayOutputStream()

    bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            100,
             bitmapArray)

   return Base64.encodeToString(
           bitmapArray.toByteArray(),
           Base64.DEFAULT
   )
}