package com.example.mobile_systems_frontend_new.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Map(
//    val map: String
//)

@Entity(tableName = "Map")
class Map(@field:PrimaryKey val map: String)
