package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter : ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = ToDoAdapter(mutableListOf())

        val rvToDoQueue : RecyclerView = findViewById(R.id.rvToDoQueue)

        rvToDoQueue.adapter = todoAdapter
        rvToDoQueue.layoutManager = LinearLayoutManager(this)

        val btnAddQuestToDo : Button = findViewById(R.id.btnAddQuestToDo)
        val btnDeleteCompletedQuests : Button = findViewById(R.id.btnDeleteCompletedQuests)
        val etToDoTitle : EditText = findViewById(R.id.etToDoTitle)

        btnAddQuestToDo.setOnClickListener {
            val questTitle = etToDoTitle.text.toString()
            if (questTitle.isNotEmpty()){
                val quest = ToDo(questTitle)
                todoAdapter.addQuest(quest)
                etToDoTitle.text.clear()
            }
        }

        btnDeleteCompletedQuests.setOnClickListener {
            todoAdapter.deleteAccomplishedQuests()
        }
    }
}