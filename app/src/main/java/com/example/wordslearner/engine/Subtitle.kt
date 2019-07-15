package com.example.wordslearner.engine

import android.util.Log
import java.io.*
import java.util.*

class Subtitle(private val path: String) {
    private val lines: List<String>
    val sentences: MutableList<String>

    init {
        this.lines = getLines()
        sentences = getListOfSentences()
    }

    private fun readFile(): String {
        val res = StringBuilder()
        val file = File(path)
        try {
            val br = BufferedReader(FileReader(file))
            while (br.ready()) {
                res.append(br.readLine() + "\n")
            }
            br.close()
        } catch (e: FileNotFoundException) {
            println(e.message)
        } catch (e: IOException) {
            println(e.message)
        }

        return res.toString()
    }

    private fun getLines(): List<String> {
        val allText = readFile().split("\n\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val res = ArrayList<String>(allText.size)
        val sb = StringBuilder()
        for (i in allText.indices) {
            val tmp = allText[i].split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (j in 2 until tmp.size) {
                sb.append(tmp[j]).append(" ")
            }

            val sent = Sentences(sb.toString()).listSentences
            sent.forEach {
                if(it != "") {
                    res.add(it)
                }
            }
            sb.delete(0, sb.length)
        }
        return res
    }

    private fun getListOfSentences(): ArrayList<String> {
        val sentences = ArrayList<String>(lines.size)
        for (line in lines) {
            sentences.add(line)
        }
        return sentences
    }
}
