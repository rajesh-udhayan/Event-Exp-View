package com.example.eventexpandableview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val json: String = assets.open("response_data.json").bufferedReader().use { it.readText() }
        val parentEventList: MutableList<ParentEvent> =
            viewModel.prepareMapForExpandableListView(json)
        val expRecyclerView = findViewById<RecyclerView>(R.id.eventRecyclerView)
        expRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EventExpandableAdapter(parentEventList)
        }
    }
}