package com.example.mobile_systems_frontend_new.repository

import androidx.annotation.WorkerThread
import com.example.mobile_systems_frontend_new.Dao.UserMapDao
import com.example.mobile_systems_frontend_new.api.RetrofitInstance
import com.example.mobile_systems_frontend_new.model.Map
import com.example.mobile_systems_frontend_new.model.UserMap
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DataRepository(private val userMapDao: UserMapDao) {

    val user: Flow<UserMap> = userMapDao.getUserMap()

    //val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userMap: UserMap) {
        userMapDao.insertUser(userMap)
    }

    suspend fun getPost(): Map {
        return RetrofitInstance.api.getPost()
    }
}