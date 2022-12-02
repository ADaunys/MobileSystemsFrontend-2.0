package com.example.mobile_systems_frontend_new.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobile_systems_frontend_new.model.Users

@Dao
interface UsersDao {
    @Insert
    fun insertUser(users: Users?)

    @Insert
    fun insertAllUsers(users: List<Users?>?)

    @Delete
    fun deleteUser(users: Users?)

    @Update
    fun updateUser(users: Users?)

    @get:Query("SELECT * FROM CalculationData")
    val allUsers: LiveData<List<Any?>?>?
}
