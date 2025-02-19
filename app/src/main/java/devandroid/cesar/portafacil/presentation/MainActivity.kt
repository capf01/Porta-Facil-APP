package com.example.encomendas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val encomendaViewModel: EncomendaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = EncomendaAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        encomendaViewModel.todasEncomendas.observe(this) { encomendas ->
            encomendas?.let { adapter.submitList(it) }
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, EncomendaFormActivity::class.java)
            startActivity(intent)
        }
    }
}

class EncomendaFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encomenda_form)

        // Implementação do formulário para adicionar ou editar encomendas
    }
}