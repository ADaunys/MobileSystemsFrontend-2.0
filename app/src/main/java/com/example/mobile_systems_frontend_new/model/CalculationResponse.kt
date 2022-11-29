package com.example.mobile_systems_frontend_new.model

data class CalculationResponse(
    val message: String,
    val macAddress: String,
    val xCoordinate: Int,
    val yCoordinate: Int,
    val minimumDistance: Float
)
