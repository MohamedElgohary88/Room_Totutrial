package com.example.room_totutrial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room_totutrial.R
import com.example.room_totutrial.data.Note

class ListAdapter( val noteList:List<Note> , private val noteClickListner: NoteClickListner):RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
        val title2 = itemView.findViewById<TextView>(R.id.txt_title)
        val description2 = itemView.findViewById<TextView>(R.id.txt_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentList = noteList[position]
        holder.title2.text = currentList.title
        holder.description2.text = currentList.description

        holder.itemView.setOnClickListener {
            noteClickListner.clickListner(currentList)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}