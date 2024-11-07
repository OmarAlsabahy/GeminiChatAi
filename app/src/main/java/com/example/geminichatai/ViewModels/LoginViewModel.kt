package com.example.geminichatai.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geminichatai.Repositories.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo:LoginRepo):ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean>
        get() = _status
    fun login(email:String , password:String){
        repo.login(email , password , {
            if (it){
                _status.value = it
            }else{
                _status.value = false
            }
        })
    }
}