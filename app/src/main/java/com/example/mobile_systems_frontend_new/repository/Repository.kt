package com.example.mobile_systems_frontend_new.repository

import com.example.mobile_systems_frontend_new.api.RetrofitInstance
import com.example.mobile_systems_frontend_new.model.Post
import okhttp3.Response

class Repository {
    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}