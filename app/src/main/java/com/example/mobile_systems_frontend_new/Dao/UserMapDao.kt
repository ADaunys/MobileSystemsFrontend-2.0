package com.example.mobile_systems_frontend_new.Dao

import androidx.room.*
import com.example.mobile_systems_frontend_new.model.UserMap
import kotlinx.coroutines.flow.Flow

@Dao
interface UserMapDao {
    //Insert user string
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userMap: UserMap)

    @Update
    suspend fun update(userMap: UserMap)

    @Query("DELETE FROM userMap_table")
    suspend fun delete()

    @Query("SELECT * FROM userMap_table")
    fun getUserMap(): Flow<UserMap>


}