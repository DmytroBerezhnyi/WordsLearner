package com.example.wordslearner.engine

class Node(val value : String,
                var n : Int = 1,
                var nextValues : ArrayList<String> = ArrayList(0),
                var prevValues : ArrayList<String> = ArrayList(0)) {
    override fun toString() = "$value $n times  prevValues $prevValues  nextValue $nextValues"
}