package com.example.wordslearner.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wordslearner.engine.Node
import kotlinx.android.synthetic.main.word_item.view.*

class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(node : Node) {
        itemView.tv_n.text = node.n.toString()
        itemView.tv_word.text = node.value
    }
}