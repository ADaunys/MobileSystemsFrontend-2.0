package com.example.mobile_systems_frontend_new

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_systems_frontend_new.model.Post
import com.example.mobile_systems_frontend_new.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Post> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}