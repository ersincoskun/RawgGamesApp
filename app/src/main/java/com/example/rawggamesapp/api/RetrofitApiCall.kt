package com.example.rawggamesapp.api

import com.example.rawggamesapp.model.Model
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiCall {

    @GET("/api/games?key=67fd2e65d6024fa8a8f6c7e860a574fd&page=2")
    suspend fun getGamesFromApi(): Response<Model.GameResult>

}