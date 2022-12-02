package com.example.mobile_systems_frontend_new.model

data class CalculationResponse(
    val responses: Array<String>,
    val responseData: Array<CalculationData>
)
