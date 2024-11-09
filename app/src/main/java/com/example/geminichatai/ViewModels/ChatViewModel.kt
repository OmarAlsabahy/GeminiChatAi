package com.example.geminichatai.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminichatai.Models.MessageModel
import com.example.geminichatai.Repositories.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repo:ChatRepo):ViewModel() {
    private val _messages = MutableLiveData<List<MessageModel>>()
    val messages : LiveData<List<MessageModel>>
        get() = _messages
    fun sendMessage(message:String){
        viewModelScope.launch (Dispatchers.IO){
            repo.sendMessage(message)
        }

    }
    fun getAllMessages(){
        repo.getAllMessages{
            val messages = it
            _messages.value = messages
        }
    }
}