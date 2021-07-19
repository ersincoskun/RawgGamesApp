package com.example.rawggamesapp.repo

import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.Resource
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameDao: GameDao,
    private val retrofitCall: RetrofitApiCall
) : GameRepositoryInterface {

    override suspend fun insertGames(gameList: List<Model.Game>) {
        gameDao.insertGames(*gameList.toTypedArray())
    }

    override suspend fun getGamesFromDb(): List<Model.Game> {
        return gameDao.getGames()
    }

    override suspend fun getGameFromDb(gameId:Int): Model.Game {
        return gameDao.getGame(gameId)
    }

    override suspend fun deleteAll() {
        gameDao.deleteAll()
    }

    override suspend fun getGamesFromApi(): Resource<Model.GameResult> {
        return try {
            val response = retrofitCall.getGamesFromApi()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }

        } catch (e: Exception) {
            Resource.error("Please check your internet connection!", null)
        }

    }

}