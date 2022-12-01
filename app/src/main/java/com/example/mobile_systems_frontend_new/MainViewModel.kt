package com.example.mobile_systems_frontend_new

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_systems_frontend_new.model.CalculationResponse
import com.example.mobile_systems_frontend_new.model.Post
import com.example.mobile_systems_frontend_new.model.Map
import com.example.mobile_systems_frontend_new.model.PostUserData
import com.example.mobile_systems_frontend_new.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Map> = MutableLiveData()
    val calculationResponse: MutableLiveData<CalculationResponse> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun calculateLocation(postUserData: PostUserData) {
        viewModelScope.launch {
            val response = repository.calculateLocation(postUserData)
            calculationResponse.value = response
        }
    }
}