package com.example.juegosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var gamesList: ArrayList<String>
    private lateinit var gamesAdapter: ArrayAdapter<String>
    private lateinit var editTextGameName: EditText
    private lateinit var listViewGames: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextGameName = findViewById(R.id.editTextGameName)
        listViewGames = findViewById(R.id.listViewGames)
        val btnAddGame: Button = findViewById(R.id.btnAddGame)

        gamesList = ArrayList()
        gamesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, gamesList)
        listViewGames.adapter = gamesAdapter

        btnAddGame.setOnClickListener {
            val gameName = editTextGameName.text.toString().trim()
            if (gameName.isNotEmpty()) {
                gamesList.add(gameName)
                gamesAdapter.notifyDataSetChanged()
                editTextGameName.setText("")
            }
        }
    }
}
