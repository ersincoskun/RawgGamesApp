package com.example.rawggamesapp.repo

import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.Resource

class FakeGameRepository : GameRepositoryInterface {

    private val emptyGame = Model.Game(0, "", "", "", 2f, 0, "", 0, 0, 0)
    private val emptyGameForApi = Model.Game(1, "", "", "", 2f, 0, "", 0, 0, 0)
    private val games = mutableListOf(emptyGame)

    override suspend fun insertGames(gameList: List<Model.Game>) {
        games.addAll(gameList)
    }

    override suspend fun getGamesFromDb(): List<Model.Game> {
        return games
    }

    override suspend fun getGameFromDb(gameId: Int): Model.Game {
        val gameList = listOf(
            Model.Game(0, "", "", "", 2f, 0, "", 0, 0, 0),
            Model.Game(1, "", "", "", 2f, 0, "", 0, 0, 0),
            Model.Game(2, "", "", "", 2f, 0, "", 0, 0, 0),
        )
        var gameIndex = 0
        var order = 0
        gameList.forEach {
            if (it.gameId == gameId) {
                gameIndex = order
            }
            order++
        }
        return gameList[gameIndex]
    }

    override suspend fun deleteAll() {
        games.clear()
    }

    override suspend fun getGamesFromApi(): Resource<Model.GameResult> {
        return Resource.Success(Model.GameResult(0, listOf(emptyGameForApi), ""))
    }
}