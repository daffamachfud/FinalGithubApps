package com.onoh.finalgithubapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onoh.finalgithubapps.model.FavoriteUser

@Database(entities = [FavoriteUser::class],version = 1,exportSchema = false)
abstract class FavoriteUserDatabase :RoomDatabase(){

    abstract fun favoriteUserDao() : FavoriteUserDao

    companion object{
        @Volatile
        private var INSTANCE:FavoriteUserDatabase? = null

        fun getDatabase(context: Context):FavoriteUserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUserDatabase::class.java,
                    "favorite_user_database"
                ).build()
                INSTANCE = instance
                    return  instance
            }
        }
    }

}