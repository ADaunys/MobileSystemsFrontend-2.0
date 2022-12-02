package com.example.mobile_systems_frontend_new.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
class Users(@field:PrimaryKey val MAC: String, val str1: Int, val str2: Int, val str3: Int)