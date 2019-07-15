package com.example.wordslearner.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordslearner.R
import com.example.wordslearner.engine.Node

class WordRecyclerAdapter(val context : Context, private val data: ArrayList<Node>) : RecyclerView.Adapter<WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordViewHolder(LayoutInflater.from(context).inflate(R.layout.word_item, parent,  false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(data[position])
    }
}