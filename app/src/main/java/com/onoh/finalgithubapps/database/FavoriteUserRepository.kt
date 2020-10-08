package com.onoh.finalgithubapps.database

import androidx.lifecycle.LiveData
import com.onoh.finalgithubapps.model.FavoriteUser

class FavoriteUserRepository(private val favoriteUserDao: FavoriteUserDao) {
    val getAllData: LiveData<List<FavoriteUser>> = favoriteUserDao.getAllFavorite()

    suspend fun addFavorite(favoriteUser: FavoriteUser) {
        favoriteUserDao.addFavorite(favoriteUser)
    }

    suspend fun deleteUser(favoriteUser: FavoriteUser) {
        favoriteUserDao.deleteFavorite(favoriteUser)
    }

    suspend fun deleteFavoriteUser(username: String){
        favoriteUserDao.deleteFavoriteUser(username)
    }
    fun isExist(username: String): LiveData<FavoriteUser> {
        return favoriteUserDao.isUserFavorite(username)
    }

}