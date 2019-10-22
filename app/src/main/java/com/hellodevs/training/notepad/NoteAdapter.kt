package com.hellodevs.training.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(val notes: List<Note>, val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardview: CardView = itemView.card_view
        val titleView: TextView = itemView.title
        val excerptView: TextView = itemView.excerpt

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.cardview.tag = position
        holder.cardview.setOnClickListener(itemClickListener)
        holder.titleView.text = note.title
        holder.excerptView.text = note.text
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}