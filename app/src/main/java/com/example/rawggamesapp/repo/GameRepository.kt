package com.example.rawggamesapp.repo

import android.widget.Toast
import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.Resource
import com.example.rawggamesapp.util.util.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
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

    override suspend fun deleteAll() {
        gameDao.deleteAll()
    }

    override suspend fun getGamesFromApi(): Resource<Model.GameResult> {

        return try {
            val response = retrofitCall.getGames(API_KEY)
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