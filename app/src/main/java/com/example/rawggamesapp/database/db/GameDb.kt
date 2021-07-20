package com.example.rawggamesapp.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.model.Model

@Database(entities = [Model.Game::class], version = 3)
abstract class GameDb:RoomDatabase() {
    abstract fun gameDao(): GameDao
}