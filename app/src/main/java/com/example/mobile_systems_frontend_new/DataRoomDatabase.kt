package com.example.mobile_systems_frontend_new.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobile_systems_frontend_new.Dao.UserMapDao
import com.example.mobile_systems_frontend_new.model.UserMap

@Database(entities = [UserMap::class], version = 1)
abstract class DataRoomDatabase : RoomDatabase() {
    abstract fun  userDataDao() : UserMapDao
}
