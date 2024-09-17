package com.example.lasttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import androidx.lifecycle.ViewModelProvider
import com.example.lasttask.AlbumAdapter
import com.example.lasttask.AlbumRepository
import com.example.lasttask.AlbumViewModel
import com.example.lasttask.AlbumViewModelFactory
import com.example.lasttask.NetworkModule

class MainActivity : AppCompatActivity() {

    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel using the factory
        val apiService = NetworkModule.provideApiService(NetworkModule.provideRetrofit())
        val repository = AlbumRepository(apiService)
        val factory = AlbumViewModelFactory(repository)
        albumViewModel = ViewModelProvider(this, factory).get(AlbumViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = AlbumAdapter()
        recyclerView.adapter = adapter

        albumViewModel.albums.observe(this, Observer {
            adapter.submitList(it)
        })

        albumViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        albumViewModel.fetchAlbums()
    }
}
