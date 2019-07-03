import java.util.*
import kotlin.NoSuchElementException

class TextAnalizer {

    public fun analyzeText(str : String): ArrayList<Node> {
        val list = ArrayList<Node>()
        println(str)
        val words = str.split(" ")

        for(i in 0 until words.size) {
            if(ifExist(i, words, list)) {continue}
            ifNotExist(i, words, list)
        }
        return list
    }

    private fun ifNotExist(i: Int, words: List<String>, list: ArrayList<Node>) {
        val node = Node(words[i])
        list.add(node)
        addPrevAndNext(i, node, words)
    }

    private fun ifExist(i: Int, words: List<String>, list: ArrayList<Node>) : Boolean {
        list.forEach {
            if (it.value == words[i]) {
                it.n++
                addPrevAndNext(i, it, words)
                return true
            }
        }
        return false
    }

    private fun addPrevAndNext(i: Int, node: Node, words: List<String>) {
        if (i - 1 >= 0) node.prevValues.add(words[i - 1])
        if (i + 1 < words.size) node.nextValues.add(words[i + 1])
    }
}
