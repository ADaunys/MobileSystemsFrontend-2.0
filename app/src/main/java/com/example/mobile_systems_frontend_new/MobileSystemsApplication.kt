package com.example.mobile_systems_frontend_new

import android.app.Application
import com.example.mobile_systems_frontend_new.repository.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MobileSystemsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { DataRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DataRepository(database.userMapDao()) }
}