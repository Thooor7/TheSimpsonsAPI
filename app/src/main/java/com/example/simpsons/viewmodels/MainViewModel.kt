package com.example.simpsons.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpsons.core.RetrofitClient
import com.example.simpsons.models.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private var _listCharacters = MutableLiveData<List<Characters>>()
    val listCharacters: LiveData<List<Characters>> get() = _listCharacters

    fun getCharacters(){

        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getCharacters()
            withContext(Dispatchers.Main){
                _listCharacters.value = response.body()
            }
        }
    }

    fun getCharacter(person: String){

        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getCharacter(person)
            withContext(Dispatchers.Main){
                _listCharacters.value = response.body()
            }
        }
    }
}