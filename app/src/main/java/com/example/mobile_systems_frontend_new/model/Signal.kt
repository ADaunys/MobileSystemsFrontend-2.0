package com.example.mobile_systems_frontend_new.model

data class Signal(
    val matavimas: Int,
    val x: Int,
    val y: Int,
    val atstumas: Float,
    val stiprumai: Array<Strength>
)
