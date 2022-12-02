package com.example.mobile_systems_frontend_new

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_systems_frontend_new.Dao.UserMapDao
import com.example.mobile_systems_frontend_new.model.UserMap

@Database(entities = arrayOf(UserMap::class), version = 1, exportSchema = false)
public abstract class DataRoomDatabase : RoomDatabase() {

    abstract fun userMapDao(): UserMapDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataRoomDatabase? = null

        fun getDatabase(context: Context): DataRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataRoomDatabase::class.java,
                    "data_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}