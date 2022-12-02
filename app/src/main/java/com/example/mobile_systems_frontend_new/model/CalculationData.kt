package com.example.mobile_systems_frontend_new.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class CalculationData(
//    val mac: String,
//    val x: Int,
//    val y: Int,
//    val distance: Float
//)


@Entity(tableName = "CalculationData")
class CalculationData(@field:PrimaryKey val matavimas: Int, val x: Int, val y: Int, val atstumas: Double)
