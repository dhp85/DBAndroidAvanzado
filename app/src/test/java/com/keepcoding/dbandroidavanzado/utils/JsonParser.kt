package com.keepcoding.dbandroidavanzado.utils

import com.google.common.io.Resources
import java.io.File

fun parseJson(path:String): String{
    val uri = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}