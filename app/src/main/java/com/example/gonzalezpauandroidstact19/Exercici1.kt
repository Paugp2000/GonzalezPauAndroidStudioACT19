package com.example.gonzalezpauandroidstact19

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Exercici1 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private val viewModel: PostViewModel by viewModels()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclerView()
        observeViewModel()
        setupButton()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setupRecyclerView() {
        adapter = PostAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Exercici1)
            this.adapter = this@Exercici1.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.posts.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Log.e(TAG, "Error: $it")
                Toast.makeText(this, "Error carregant posts: $it", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            val button = findViewById<Button>(R.id.btnLoadPosts)
            button.isEnabled = !isLoading
            button.text = if (isLoading) "Carregant..." else "Carregar 100 Posts"
        }
    }

    private fun setupButton() {
        findViewById<Button>(R.id.btnLoadPosts).setOnClickListener {
            viewModel.loadPosts()
        }
    }
}