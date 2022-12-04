package com.example.mobile_systems_frontend_new.Dao

import androidx.room.*
import com.example.mobile_systems_frontend_new.model.InputData
import com.example.mobile_systems_frontend_new.model.UserMap

@Dao
interface DaoFile {
    //Insert user string       (onConflict = OnConflictStrategy.IGNORE)
    @Insert
    fun insert(vararg userMap: UserMap)

    @Update
    fun update(userMap: UserMap)

    @Query("DELETE FROM userMap_table")
    fun delete()

    @Query("SELECT * FROM userMap_table")
    fun getUserMap(): List<UserMap>

    // ------- InputData --------

    @Insert
    fun insertInputData(vararg inputData: InputData)

    @Update
    fun updateInputData(inputData: InputData)

    @Query("DELETE FROM InputData_table")
    fun deleteInputData()

    @Query("SELECT * FROM InputData_table")
    fun getInputData(): List<InputData>
}