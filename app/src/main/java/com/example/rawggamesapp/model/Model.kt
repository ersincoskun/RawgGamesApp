package com.example.rawggamesapp.model

import com.google.gson.annotations.SerializedName

class Model {

    data class GameResult(
        val count:Int,
        val results:List<Game>
    )

    data class Game(
        val id:Int,
        val name:String,
        val released:String,
        @SerializedName("background_image")
        val imageUrl:String,
        val rating:Int,
        val playtime:Int
    )

}