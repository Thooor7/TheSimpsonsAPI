package com.example.simpsons.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpsons.databinding.ActivityMainBinding
import com.example.simpsons.viewmodels.MainViewModel
import com.example.simpsons.views.adapter.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCharacters()

        onClick()
        setupRecycleView()
        observer()

    }

    private fun onClick() {
        binding.tilSearch.setEndIconOnClickListener{
            if (binding.tietSearch.text.toString() == ""){
                viewModel.getCharacters()
            } else {
                viewModel.getCharacter(binding.tietSearch.text.toString().trim())
            }
        }
    }

    private fun observer() {
        viewModel.listCharacters.observe(this, Observer {
           adapter.listaPersonajes = it
            adapter.notifyDataSetChanged()
        })
    }

    fun setupRecycleView(){
        binding.rvCharacters.layoutManager = GridLayoutManager(this, 3)
        adapter = CharacterAdapter(this, arrayListOf())
        binding.rvCharacters.adapter = adapter
    }





}

