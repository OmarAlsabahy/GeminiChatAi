package com.example.geminichatai.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geminichatai.Repositories.RegisterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(val repo:RegisterRepo) : ViewModel(){
    private val _status = MutableLiveData<Boolean>()
    val status:LiveData<Boolean>
        get() = _status
    fun register(email:String , password:String){
        repo.register(email , password , {
            if (it){
                _status.value = it
            }else{
                _status.value = false
            }
        })
    }

}