package com.example.rawggamesapp.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.model.Model

@Database(entities = [Model.Game::class], version = 1)
abstract class GameDb:RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {

        @Volatile
        private var instance: GameDb? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GameDb::class.java,
            "gameDb"
        ).fallbackToDestructiveMigrationFrom(1)
            .build()

    }

}