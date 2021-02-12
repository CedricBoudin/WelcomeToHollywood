package com.example.welcometohollywood.Models

import android.content.Context
import com.example.welcometohollywood.GameActivity
import java.io.File

class DataFile {

    @ExperimentalStdlibApi
    fun readFile(filename: String, context: Context) {
        //var path = context.getExternalFilesDir(null).toString()
        val path = context.filesDir.absolutePath
        val file = File("$path/../Models/movieTest.txt")

        var lines: MutableList<String> = file.readLines() as MutableList<String>
        println("RRRRRREEEEEEMMMMMMOOOOOOOVVVVVVEEEEEE")
        println(lines.removeFirst())
        lines.forEach { line ->
            val movieData = line.split(':')
        }

    }
}