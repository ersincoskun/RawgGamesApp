package com.example.rawggamesapp.repo

import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.Resource

class FakeGameRepository : GameRepositoryInterface {

    private val games = mutableListOf<Model.Game>()

    override suspend fun insertGames(gameList: List<Model.Game>) {
        games.addAll(gameList)
    }

    override suspend fun getGamesFromDb(): List<Model.Game> {
        return games
    }

    override suspend fun getGameFromDb(gameId: Int): Model.Game {
        //return games
        TODO()
    }

    override suspend fun deleteAll() {
        games.clear()
    }

    override suspend fun getGamesFromApi(): Resource<Model.GameResult> {
        return Resource.success(Model.GameResult(0, listOf(), ""))
    }
}