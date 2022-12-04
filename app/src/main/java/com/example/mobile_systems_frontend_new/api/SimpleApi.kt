package com.example.mobile_systems_frontend_new.api

import com.example.mobile_systems_frontend_new.model.*
import com.example.mobile_systems_frontend_new.model.Map
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {

    @GET("/")
    suspend fun getPost(): Map

    @POST("/")
    suspend fun calculateLocation(
        @Body postUserData: PostUserData
    ): CalculationResponse

    @GET("/signals")
    suspend fun getSignals(): Messages
}