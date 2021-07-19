package com.example.rawggamesapp.api

import com.example.rawggamesapp.model.Model
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiCall {

    @GET("/api/games?key={key}")
    suspend fun getGames(
        @Path("key") key: String
    ): retrofit2.Call<Model.GameResult>

}