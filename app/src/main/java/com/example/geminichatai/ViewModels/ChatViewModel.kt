package com.example.geminichatai.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminichatai.Repositories.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repo:ChatRepo):ViewModel() {
    fun sendMessage(message:String){
        viewModelScope.launch (Dispatchers.IO){
            repo.sendMessage(message)
        }

    }
}