package com.route.notesapplicationc32.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.notesapplicationc32.R
import com.route.notesapplicationc32.database.Note


/**
 * Created by Mohamed Nabil Mohamed on 6/10/2020.
 * m.nabil.fci2015@gmail.com
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notes = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.title.text = note.title
        holder.content.text = note.desc
    }

    fun removeItem(position: Int): Note {
        val noteToDelete = notes.removeAt(position);
        notifyItemRemoved(position);
        return noteToDelete;
    }
    fun changeData(list: List<Note>) {
        this.notes = list.toMutableList();
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView;
        var content: TextView

        init {
            title = itemView.findViewById(R.id.title)
            content = itemView.findViewById(R.id.content)
        }

    }
}