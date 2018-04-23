package main

import java.io.File
import java.io.IOException
import java.io.InputStream

const val RESOURCES_FOLDER = "/resources/"

fun getResource(path: String): InputStream? = Application::class.java.classLoader.getResourceAsStream(RESOURCES_FOLDER + path)

fun getResourceFromFileSafety(path: String): InputStream{
    val file: File? = File(path)
    file?.exists() ?: file!!.createNewFile()
    return file!!.inputStream()
}
