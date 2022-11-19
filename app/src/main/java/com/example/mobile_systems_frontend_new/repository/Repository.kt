package com.example.mobile_systems_frontend_new.repository

import com.example.mobile_systems_frontend_new.api.RetrofitInstance
import com.example.mobile_systems_frontend_new.model.Post
import com.example.mobile_systems_frontend_new.model.Map
import okhttp3.Response

class Repository {
    suspend fun getPost(): Map {
        return RetrofitInstance.api.getPost()
    }
}