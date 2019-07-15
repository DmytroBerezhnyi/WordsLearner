package com.example.wordslearner.engine.comparator

import com.example.wordslearner.engine.Node


class ComparatorByN : Comparator<Node> {
    override fun compare(node1: Node, node2: Node) = if (node1.n < node2.n) 1 else if (node1.n == node2.n) 0 else -1
}