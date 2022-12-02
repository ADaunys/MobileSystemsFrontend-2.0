package com.example.mobile_systems_frontend_new.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobile_systems_frontend_new.model.Map

@Dao
interface MapDao {
    @Insert
    fun insertMap(map: Map?)

    @Delete
    fun deleteMap(map: Map?)

    @Update
    fun updateMap(map: Map?)

}