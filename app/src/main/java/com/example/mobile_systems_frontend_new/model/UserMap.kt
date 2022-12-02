package com.example.mobile_systems_frontend_new.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Entity(tableName = "userMap_table")
class UserMap(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "map") val map: String?,
)