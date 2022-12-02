package com.example.mobile_systems_frontend_new


import android.content.Context
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mobile_systems_frontend_new.api.RetrofitInstance
import com.example.mobile_systems_frontend_new.dao.CalculationDao
import com.example.mobile_systems_frontend_new.dao.MapDao
import com.example.mobile_systems_frontend_new.dao.UsersDao
import com.example.mobile_systems_frontend_new.model.CalculationData
import com.example.mobile_systems_frontend_new.model.Users
import com.example.mobile_systems_frontend_new.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Database(entities = [CalculationData::class, Users::class, Map::class], version = 1)
public abstract class MapRoomDatabase: RoomDatabase(){
    
    abstract fun calculationDao(): CalculationDao
    abstract fun userDao(): UsersDao
    abstract fun mapDao(): MapDao

    private var instance: MapRoomDatabase? = null
    //private val retrofit: Retrofit? = null
    private val context_copy: Context? = null

    companion object {
        @Volatile
        private var INSTANCE: MapRoomDatabase? = null

        fun getDatabase(context: Context): MapRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MapRoomDatabase::class.java,
                    "map_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    //create a static method to get the database instance
    fun getInstance(context: Context): MapRoomDatabase? {
        if (instance == null) {
            synchronized(MapRoomDatabase::class) {
                instance = Room.databaseBuilder(
                    context_copy!!.applicationContext,
                    MapRoomDatabase::class.java, "map_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        return instance
    }

    //create an initial callback to populate the database
    private val sRoomDatabaseCallback: Callback = object : Callback() {
        override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            PopulateDbAsync(INSTANCE);
        }
    }

    private class PopulateDbAsync internal constructor(db: MapRoomDatabase?): AsyncTask<Void?, Void?, Void?>() {

        private val calculationDao: CalculationDao? = null
        private val mapDao: MapDao? = null
        private val usersDao: UsersDao? = null
        //private val

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val mDao: CalculationDao


        init {
            mDao = db!!.calculationDao()
        }
    }
}