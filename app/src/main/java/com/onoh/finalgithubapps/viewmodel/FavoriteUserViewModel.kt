package com.onoh.finalgithubapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.onoh.finalgithubapps.database.FavoriteUserDatabase
import com.onoh.finalgithubapps.database.FavoriteUserRepository
import com.onoh.finalgithubapps.model.FavoriteUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteUserViewModel(application: Application) :AndroidViewModel(application) {

    val getAll:LiveData<List<FavoriteUser>>
    private val repository:FavoriteUserRepository

    init{
        val favoriteUserDao = FavoriteUserDatabase.getDatabase(application).favoriteUserDao()
        repository = FavoriteUserRepository(favoriteUserDao)
        getAll = repository.getAllData
    }

    fun addFavorite(favoriteUser:FavoriteUser){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavorite(favoriteUser)
        }
    }

    fun deleteUser(favoriteUser: FavoriteUser){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(favoriteUser)
        }
    }

    fun deleteFavoriteUser(username: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavoriteUser(username)
        }
    }

    fun isExist(username:String) = repository.isExist(username)


}