package com.example.mobile_systems_frontend_new.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobile_systems_frontend_new.model.CalculationData

@Dao
interface CalculationDao {
    @Insert
    fun insertCalculation(calculation: CalculationData?)

    @Insert
    fun insertAllCalculations(calculation: List<CalculationData?>?)

    @Delete
    fun deleteCalculation(calculation: CalculationData?)

    @Update
    fun updateCalculation(calculation: CalculationData?)

    @get:Query("SELECT * FROM CalculationData")
    val allCalculations: LiveData<List<Any?>?>?
}