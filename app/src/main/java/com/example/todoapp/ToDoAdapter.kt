package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ToDoAdapter(
    private val quests: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.quest_todo,
                parent,
                false
            )
        )
    }

    fun addQuest(quest: ToDo){
        quests.add(quest)
        notifyItemInserted(quests.size - 1)
    }

    fun deleteAccomplishedQuests(){
        quests.removeAll {quest ->
            quest.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeTrough(tvQuestTitle: TextView, isChecked: Boolean) {
        if(isChecked){
            tvQuestTitle.paintFlags = tvQuestTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvQuestTitle.paintFlags = tvQuestTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentQuest = quests[position]
        holder.itemView.apply {
            val tvQuestTitle : TextView = findViewById(R.id.tvQuestTitle)
            val cbQuestAccomplished: CheckBox = this.findViewById(R.id.cbQuestAccomplished)
            tvQuestTitle.text = currentQuest.title
            cbQuestAccomplished.isChecked = currentQuest.isChecked
            toggleStrikeTrough(tvQuestTitle, currentQuest.isChecked)
            cbQuestAccomplished.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeTrough(tvQuestTitle, isChecked)
                currentQuest.isChecked = !currentQuest.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return quests.size
    }
}