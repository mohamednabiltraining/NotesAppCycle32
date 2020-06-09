package com.route.notesapplicationc32

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.notesapplicationc32.database.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private var list = listOf<Note>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title);
        var desc: TextView = itemView.findViewById(R.id.desc);
        var dt: TextView = itemView.findViewById(R.id.dt);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list?.size ?: 0
    }

    fun getNote(position: Int): Note {
        return list!![position];
    }

    fun changeData(list: List<Note>?) {
        this.list = list!!
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var note: Note = list!![position];
        holder.title.text = note.title;
        holder.desc.text = note.desc;
        holder.dt.text = note.date;

        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener?.onItemClick(
                note
            )
        })
    }

    var onItemClickListener: OnItemClickListener? = null;

    interface OnItemClickListener {
        fun onItemClick(note: Note?)
    }

}