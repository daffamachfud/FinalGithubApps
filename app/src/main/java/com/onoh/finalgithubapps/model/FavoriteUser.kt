package com.onoh.finalgithubapps.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="favorite_user")
data class FavoriteUser(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val columnUsername:String,
    val columnAvatar:String,
    val columnCompany :String,
    val columnLocation :String
)