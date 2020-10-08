package com.onoh.finalgithubapps.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.onoh.finalgithubapps.model.FavoriteUser

@Dao
interface FavoriteUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteUser:FavoriteUser)

    @Delete
    suspend fun deleteFavorite(favoriteUser: FavoriteUser)


    @Query("DELETE FROM favorite_user WHERE columnUsername = :username")
    suspend fun deleteFavoriteUser(username: String)

    @Query("SELECT * FROM favorite_user ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite_user WHERE columnUsername = :username LIMIT 1")
    fun isUserFavorite(username: String) :LiveData<FavoriteUser>
}