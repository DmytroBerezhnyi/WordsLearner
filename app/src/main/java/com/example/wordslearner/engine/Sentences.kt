package com.example.wordslearner.engine

import java.text.*
import java.util.*

class Sentences(text: String) {
    val listSentences: List<String>

    init {
        listSentences = getListOfSentences(text)
    }

    private fun getListOfSentences(text: String): List<String> {
        val res = ArrayList<String>()
        val iterator = BreakIterator.getSentenceInstance(Locale.ENGLISH)
        iterator.setText(text)
        var start = iterator.first()
        var end = iterator.next()
        while (end != BreakIterator.DONE) {
            val tmp = text.substring(start, end).replace("[^a-zA-Z -']".toRegex(), "").trim { it <= ' ' }
                .replace('!', ' ', true) // remove redundant double replacement
                .trim()
            if (tmp != "") { //tmp != null &&
                res.add(tmp)
            }
            start = end
            end = iterator.next()
        }
        return res
    }
}

