package com.example.simpsons.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpsons.databinding.ActivityMainBinding
import com.example.simpsons.viewmodels.MainViewModel
import com.example.simpsons.views.adapter.CharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private val mviewModel: MainViewModel by viewModel()
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mviewModel.getCharacters()

        onClick()
        setupRecycleView()
        observer()

    }

    private fun onClick() {
        binding.tilSearch.setEndIconOnClickListener{
            if (binding.tietSearch.text.toString() == ""){
                mviewModel.getCharacters()
            } else {
                mviewModel.getCharacter(binding.tietSearch.text.toString().trim())
            }
        }
    }

    private fun observer() {
        mviewModel.listCharacters.observe(this, Observer {
           adapter.listCharacters = it
            adapter.notifyDataSetChanged()
        })
    }

    fun setupRecycleView(){
        binding.rvCharacters.layoutManager = GridLayoutManager(this, 3)
        adapter = CharacterAdapter(this, arrayListOf())
        binding.rvCharacters.adapter = adapter
    }





}

