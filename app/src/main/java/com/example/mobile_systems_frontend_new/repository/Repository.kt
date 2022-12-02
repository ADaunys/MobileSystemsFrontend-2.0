package com.example.mobile_systems_frontend_new.repository

import com.example.mobile_systems_frontend_new.api.RetrofitInstance
import com.example.mobile_systems_frontend_new.model.*
import com.example.mobile_systems_frontend_new.model.Map
import okhttp3.Response

class Repository {
    suspend fun getPost(): Map {
        return RetrofitInstance.api.getPost()
    }
    suspend fun calculateLocation(postUserData: PostUserData): CalculationResponse {
        return RetrofitInstance.api.calculateLocation(postUserData)
    }
    suspend fun getSignals(): Signals {
        return RetrofitInstance.api.getSignals()
    }
}