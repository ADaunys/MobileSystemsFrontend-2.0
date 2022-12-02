package com.example.mobile_systems_frontend_new.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InputData_table")
class InputData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "str1") val str1: Int?,
    @ColumnInfo(name = "str2") val str2: Int?,
    @ColumnInfo(name = "str3") val str3: Int?
)