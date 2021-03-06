package com.example.rawggamesapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rawggamesapp.model.Model

@Dao
interface GameDao {

    @Insert
    suspend fun insertGames(vararg gameList: Model.Game)

    @Query("SELECT * FROM games")
    suspend fun getGames(): List<Model.Game>

    @Query("SELECT * FROM games WHERE gameId= :gameId")
    suspend fun getGame(gameId:Int): Model.Game

    @Query("DELETE FROM games ")
    suspend fun deleteAll()

}