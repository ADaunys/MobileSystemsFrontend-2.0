package com.example.mobile_systems_frontend_new

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mobile_systems_frontend_new.Dao.UserMapDao
import com.example.mobile_systems_frontend_new.model.UserMap
import com.example.mobile_systems_frontend_new.repository.DataRepository
import com.example.mobile_systems_frontend_new.repository.Repository
import com.example.mobile_systems_frontend_new.viewModels.UserMapViewModel
import com.example.mobile_systems_frontend_new.viewModels.UserMapViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(UserMap::class), version = 1, exportSchema = false)
public abstract class DataRoomDatabase : RoomDatabase() {

    private lateinit var userMapViewModel: UserMapViewModel

    abstract fun userMapDao(): UserMapDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DataRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataRoomDatabase::class.java,
                    "data_database"
                ).addCallback(DatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class DatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        private lateinit var userMapViewModel: UserMapViewModel

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userMapDao())
                }
            }
        }

        suspend fun populateDatabase(userMapDao: UserMapDao) {
            // Delete all content here.
            userMapDao.delete()

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            userMapViewModel = ViewModelProvider(this, viewModelFactory).get(UserMapViewModel::class.java)

            userMapViewModel.getPost()
            userMapViewModel.myResponse.observe(this, Observer{ response ->
                val mapText: TextView = findViewById(R.id.mapTextView)
                var map = response.map
                Log.d("Response", map)
                map = map.replace("1", "■")
                map = map.replace("0", "□")
                mapText.text = map
                mapText.movementMethod = ScrollingMovementMethod()
            })
            
        }
    }

}
