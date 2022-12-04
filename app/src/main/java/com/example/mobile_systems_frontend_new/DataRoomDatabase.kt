package com.example.mobile_systems_frontend_new.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobile_systems_frontend_new.Dao.DaoFile
import com.example.mobile_systems_frontend_new.model.InputData
import com.example.mobile_systems_frontend_new.model.UserMap

@Database(entities = [UserMap::class, InputData::class], version = 1)
abstract class DataRoomDatabase : RoomDatabase() {
    abstract fun  dao() : DaoFile
}