package com.route.notesapplicationc32.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.notesapplicationc32.R
import com.route.notesapplicationc32.database.Note

class NoteAdapter(var data: List<Note>?) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun changeData(item: List<Note>?) {
        this.data = item
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model = data?.get(position)
        holder.title.text = model?.title
        holder.date.text = model?.date

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title_tv)
        var date: TextView = itemView.findViewById(R.id.item_date_tv)

    }
}