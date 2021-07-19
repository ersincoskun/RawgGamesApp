package com.example.rawggamesapp.repo

import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.Resource

interface GameRepositoryInterface {

    suspend fun insertGames(gameList: List<Model.Game>)

    suspend fun getGamesFromDb(): List<Model.Game>

    suspend fun deleteAll()

    suspend fun getGamesFromApi(): Resource<Model.GameResult>

}